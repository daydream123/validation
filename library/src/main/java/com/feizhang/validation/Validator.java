package com.feizhang.validation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.feizhang.validation.annotations.Len;
import com.feizhang.validation.annotations.Max;
import com.feizhang.validation.annotations.Min;
import com.feizhang.validation.annotations.NotBlank;
import com.feizhang.validation.annotations.NotEmpty;
import com.feizhang.validation.annotations.NotNull;
import com.feizhang.validation.annotations.Size;
import com.feizhang.validation.validator.ConstraintValidator;
import com.feizhang.validation.validator.LenValidator;
import com.feizhang.validation.validator.MaxValidator;
import com.feizhang.validation.validator.MinValidator;
import com.feizhang.validation.validator.NotBlankValidator;
import com.feizhang.validation.validator.NotEmptyValidator;
import com.feizhang.validation.validator.NotNullValidator;
import com.feizhang.validation.validator.SizeValidator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;

/**
 * A validator to validate fields of Object with Annotations(@Max, @Min, @NotEmpty, @NotNull, @Rang, etc.),
 * if someone field's value did't pass the validations, it'll toast a message specified by its annotation.
 */
public class Validator {
    private static final String TAG = "Validator";

    /**
     * Check if empty field
     */
    public static boolean isEmpty(@NonNull Context context, @NonNull String value, @NonNull String fieldName){
        if (TextUtils.isEmpty(value)){
            Toast.makeText(context, context.getString(R.string.empty_warning, fieldName), Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;
    }

    /**
     * startActivity and validate the intent params.
     */
    public static void startActivity(@NonNull Context context, @NonNull IntentParams params){
        if(validate(context, params)){
            if (params.getBundle() != null){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    context.startActivity(params.toIntent(context), params.getBundle());
                } else {
                    context.startActivity(params.toIntent(context));
                }
            } else {
                context.startActivity(params.toIntent(context));
            }
        }
    }

    /**
     * startService and validate the intent params.
     */
    public static void startService(@NonNull Context context, @NonNull IntentParams params){
        if (validate(context, params)){
            context.startService(params.toIntent(context));
        }
    }

    /**
     * startActivityForResult and validate the intent params.
     */
    public static void startActivityForResult(@NonNull Activity activity, @NonNull IntentParams params, int reqCode){
        if (validate(activity, params)){
            if (params.getBundle() != null){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    activity.startActivityForResult(params.toIntent(activity), reqCode, params.getBundle());
                } else {
                    activity.startActivityForResult(params.toIntent(activity), reqCode);
                }
            } else {
                activity.startActivityForResult(params.toIntent(activity), reqCode);
            }
        }
    }

    /**
     * startForegroundService and validate the intent params.
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void startForegroundService(Context context, IntentParams params){
        if (validate(context, params)){
            context.startForegroundService(params.toIntent(context));
        }
    }

    /**
     * ignore String, Number and Boolean
     */
    public static boolean validate(Context context, Object object){
        if (object != null
                && !(object instanceof String)
                && !(object instanceof Number)
                && !(object instanceof Boolean)) {
            return doValidate(context, object);
        }

        return true;
    }

    /**
     * validate fields under object.
     */
    private static boolean doValidate(Context context, Object object) {
        if (object == null) {
            return true;
        }

        // validate fields in java.util.List
        if (object instanceof List) {
            List list = (List) object;
            for (Object item : list) {
                boolean valid = validate(context, item);
                if (!valid) {
                    return false;
                }
            }

            return true;
        }

        // validate values in map
        if (object instanceof Map){
            Map map = (Map) object;

            // validate values
            for (Object next : map.values()){
                boolean valid = doValidate(context, next);
                if (!valid) {
                    return false;
                }
            }

            return true;
        }

        // validate fields in array
        if (object.getClass().isArray()) {
            Object[] array = (Object[]) object;
            for (Object item : array) {
                boolean valid = validate(context, item);
                if (!valid) {
                    return false;
                }
            }

            return true;
        }

        // validate fields of object
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            // ignore static and final
            int modifiers = field.getModifiers();
            boolean isStatic = Modifier.isStatic(modifiers);
            boolean isFinal = Modifier.isFinal(modifiers);
            if (isStatic || isFinal) {
                continue;
            }

            try {
                field.setAccessible(true);
                Object value = field.get(object);
                Annotation[] annotations = field.getAnnotations();
                if (annotations.length > 0) {
                    for (Annotation annotation : annotations) {
                        // validate object its self
                        boolean valid = validateWithAnnotation(context, annotation, value, field.getName());
                        if (!valid) {
                            return false;
                        }
                    }
                }

                // validate fields of object, but make sure it's not String or Number
                boolean valid = validate(context, value);
                if (!valid) {
                    return false;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    /**
     * validate value with annotations that we support.
     *
     * @param context    Android context
     * @param annotation annotation to validate with
     * @param object     object to validate
     * @return return true when the object passes validation
     * or the annotation we don't support
     */
    private static <A extends Annotation> boolean validateWithAnnotation(Context context,
                                                                         A annotation,
                                                                         Object object,
                                                                         String fieldName) {
        ConstraintValidator validator = null;
        if (annotation instanceof NotNull) {
            validator = new NotNullValidator((NotNull) annotation, fieldName);
        } else if (annotation instanceof NotEmpty) {
            validator = new NotEmptyValidator((NotEmpty) annotation, fieldName);
        } else if (annotation instanceof Min) {
            validator = new MinValidator((Min) annotation, fieldName);
        } else if (annotation instanceof Max) {
            validator = new MaxValidator((Max) annotation, fieldName);
        } else if (annotation instanceof Size) {
            validator = new SizeValidator((Size) annotation, fieldName);
        } else if (annotation instanceof Len){
            validator = new LenValidator((Len) annotation, fieldName);
        } else if (annotation instanceof NotBlank){
            validator = new NotBlankValidator((NotBlank) annotation, fieldName);
        }

        if (validator != null) {
            boolean valid = validator.isValid(object);
            if (!valid) {
                Log.e(TAG, validator.getMessage(object));
                Toast.makeText(context, validator.getMessage(object), Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        return true;
    }
}
