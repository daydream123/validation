package com.feizhang.validation;

import com.feizhang.validation.annotations.NotEmpty;

import java.util.Collection;

/**
 * Validator on collection object.
 */
class NotEmptyValidator extends ConstraintValidator<NotEmpty> {

    NotEmptyValidator(NotEmpty annotation, String fieldName) {
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

        if (value instanceof Collection) {
            return !((Collection) value).isEmpty();
        }

        throw new IllegalArgumentException(value + " is not a collection type");
    }
}
