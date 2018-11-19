package com.feizhang.validation;

import android.text.TextUtils;
import com.feizhang.validation.annotations.NotBlank;

/**
 * Validator on String object.
 */
class NotBlankValidator extends ConstraintValidator<NotBlank> {

    NotBlankValidator(NotBlank annotation, String fieldName) {
        super(annotation, fieldName);
    }

    @Override
    public String getMessage() {
        String message = annotation.message();
        if (message.contains("%")){
            return String.format(annotation.message(), fieldName);
        } else {
            return message;
        }
    }

    @Override
    public boolean isValid(Object value) {
        if (value == null) {
            return false;
        }

        if (value instanceof String){
            return !TextUtils.isEmpty((CharSequence) value);
        }

        throw new IllegalArgumentException(value + " is not a string type");
    }
}
