package com.crimsonhub.CrimsonFinanceAPI.custom;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class AllowedStringValuesValidator implements ConstraintValidator<AllowedStringValues, String> {

    private String[] allowedValues;

    @Override
    public void initialize(AllowedStringValues annotation) {
        this.allowedValues = annotation.allowedValues();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;
        return Arrays.asList(allowedValues).contains(value);
    }
}
