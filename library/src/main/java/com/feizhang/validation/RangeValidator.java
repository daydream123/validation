package com.feizhang.validation;

import com.feizhang.validation.annotations.Range;

/**
 * Validator on number object.
 */
class RangeValidator extends ConstraintValidator<Range> {

    RangeValidator(Range annotation, String fieldName) {
        super(annotation, fieldName);
    }

    @Override
    public String getMessage() {
        String message = annotation.message();
        if (message.contains("%")) {
            return String.format(annotation.message(), fieldName,
                    annotation.min(), annotation.max());
        } else {
            return message;
        }
    }

    @Override
    public boolean isValid(Object value) {
        int minValue = annotation.min();
        int maxValue = annotation.max();

        if (value instanceof Number) {
            int number = ((Number) value).intValue();
            return number >= minValue && number <= maxValue;
        }

        throw new IllegalArgumentException(value + " is not a number type");
    }
}
