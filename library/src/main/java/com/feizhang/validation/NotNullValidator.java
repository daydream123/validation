package com.feizhang.validation;

import com.feizhang.validation.annotations.NotNull;

/**
 * Validator on not primary object.
 */
class NotNullValidator extends ConstraintValidator<NotNull> {

    NotNullValidator(NotNull annotation, String fieldName) {
        super(annotation, fieldName);
    }

    @Override
    public String getMessage() {
        String message = annotation.message();
        if (message.contains("%")) {
            return String.format(annotation.message(), fieldName);
        } else {
            return message;
        }
    }

    @Override
    public boolean isValid(Object value) {
        return value != null;
    }
}
