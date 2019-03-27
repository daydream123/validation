package com.feizhang.validation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;

import java.io.Serializable;

public class IntentParams implements Serializable {
    public static final String EXTRA_INTENT_PARAMS = "intentParams";

    private Class<?> clazz;
    private String action;
    private Bundle bundle;

    protected IntentParams(@NonNull Class<?> clazz){
        this.clazz = clazz;
    }

    protected IntentParams(@NonNull String action){
        this.action = action;
    }

    public void setBundle(Bundle bundle){
        this.bundle = bundle;
    }

    public static IntentParams with(@NonNull Class<?> clazz){
        return new IntentParams(clazz);
    }

    public static IntentParams with(@NonNull String action){
        return new IntentParams(action);
    }

    private Intent toIntent(Context context){
        Intent intent = new Intent();
        if (clazz != null){
            intent.setClass(context, clazz);
            intent.putExtra(EXTRA_INTENT_PARAMS, this);
        } else if (!TextUtils.isEmpty(action)){
            intent.setAction(action);
            intent.putExtra(EXTRA_INTENT_PARAMS, this);
        }

        return intent;
    }

    private Bundle getBundle() {
        return bundle;
    }

    /**
     * startActivity and validate the intent params.
     */
    public void startActivity(@NonNull Context context, @NonNull IntentParams params){
        if(Validator.validate(context, params)){
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
    public void startService(@NonNull Context context, @NonNull IntentParams params){
        if (Validator.validate(context, params)){
            context.startService(params.toIntent(context));
        }
    }

    /**
     * startActivityForResult and validate the intent params.
     */
    public void startActivityForResult(@NonNull Activity activity, @NonNull IntentParams params, int reqCode){
        if (Validator.validate(activity, params)){
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
        if (Validator.validate(context, params)){
            context.startForegroundService(params.toIntent(context));
        }
    }

    /**
     * sendBroadcast and validate the intent params.
     */
    public void sendBroadcast(@NonNull Context context, @NonNull IntentParams params){
        if (Validator.validate(context, params)){
            context.sendBroadcast(params.toIntent(context));
        }
    }

    /**
     * sendBroadcast with receive permission and validate the intent params.
     */
    public void sendBroadcast(@NonNull Context context, @NonNull IntentParams params, @NonNull String permission){
        if (Validator.validate(context, params)){
            Intent intent = params.toIntent(context);
            if (TextUtils.isEmpty(intent.getAction())){
                throw new IllegalArgumentException("no action set for sending broadcast");
            }

            context.sendBroadcast(params.toIntent(context), permission);
        }
    }
}
