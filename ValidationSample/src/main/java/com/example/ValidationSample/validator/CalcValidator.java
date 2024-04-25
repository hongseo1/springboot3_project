package com.example.ValidationSample.validator;

import com.example.ValidationSample.form.CalcForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CalcValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        //체크 대상인지 아닌지(체크하는 역할)를 리턴
        return CalcForm.class.isAssignableFrom(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) {
        CalcForm form = (CalcForm) target;

        if(form.getLeftNum() != null && form.getRightNum() != null){
            if(!((form.getLeftNum() % 2 == 1) && (form.getRightNum() % 2 == 0))){
                errors.reject("com.example.ValidationSample.validator.CalcValidator.message");
            }
        }
    }
}
