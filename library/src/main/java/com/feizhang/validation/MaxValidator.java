package com.feizhang.validation;

import com.feizhang.validation.annotations.Max;

/**
 * Validator on number object.
 */
class MaxValidator extends ConstraintValidator<Max> {

    MaxValidator(Max annotation, String fieldName) {
        super(annotation, fieldName);
    }

    @Override
    public String getMessage() {
        String message = annotation.message();
        if (message.contains("%")) {
            return String.format(annotation.message(), fieldName, annotation.max());
        } else {
            return message;
        }
    }

    @Override
    public boolean isValid(Object value) {
        int maxValue = annotation.max();

        if (value instanceof Number) {
            return ((Number) value).intValue() <= maxValue;
        }

        throw new IllegalArgumentException(value + " is not a number type");
    }
}
