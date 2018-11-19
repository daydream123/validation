package com.feizhang.validation;

import com.feizhang.validation.annotations.Min;

/**
 * Validator on number object.
 */
class MinValidator extends ConstraintValidator<Min> {

    MinValidator(Min annotation, String fieldName) {
        super(annotation, fieldName);
    }

    @Override
    public String getMessage() {
        String message = annotation.message();
        if (message.contains("%")) {
            return String.format(annotation.message(), fieldName, annotation.min());
        } else {
            return message;
        }
    }

    @Override
    public boolean isValid(Object value) {
        int minValue = annotation.min();

        if (value instanceof Number) {
            return ((Number) value).intValue() >= minValue;
        }

        throw new IllegalArgumentException(value + " is not a number type");
    }
}
