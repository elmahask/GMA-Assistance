package com.gma.assistance.gma.validation;

import com.gma.assistance.gma.dto.OperatorDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        OperatorDto user = (OperatorDto) obj;
        return user.getPassword().equals(user.getConfirmPassword());
    }
}