package com.feizhang.validation;

import com.feizhang.validation.annotations.Len;

/**
 * Validator on String object.
 */
public class LenValidator extends ConstraintValidator<Len> {

    LenValidator(Len annotation, String fieldName) {
        super(annotation, fieldName);
    }

    @Override
    public boolean isValid(Object value) {
        if (value == null){
            return false;
        }

        int len = annotation.len();
        if (value instanceof String) {
            return ((String) value).length() == len;
        }

        throw new IllegalArgumentException(value + " is not a string type");
    }

    @Override
    public String getMessage() {
        String message = annotation.message();
        if (message.contains("%")) {
            return String.format(annotation.message(), fieldName, annotation.len());
        } else {
            return message;
        }
    }
}
