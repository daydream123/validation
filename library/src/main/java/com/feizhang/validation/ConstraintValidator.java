package com.feizhang.validation;

import java.lang.annotation.Annotation;

public abstract class ConstraintValidator<A extends Annotation> {
    protected A annotation;
    protected String fieldName;

    public abstract boolean isValid(Object value);

    ConstraintValidator(A annotation, String fieldName){
        this.annotation = annotation;
        this.fieldName = fieldName;
    }

    public abstract String getMessage();
}
