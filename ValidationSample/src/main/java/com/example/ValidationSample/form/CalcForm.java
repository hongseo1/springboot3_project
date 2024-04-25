package com.example.ValidationSample.form;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
/*Model = calcForm(클래스 이름과 자동 연결됨, 대신 소문자로 시작.)*/
public class CalcForm {
    @NotNull
    @Range(min=1, max=10)
    private Integer leftNum;

    @NotNull
    @Range(min=1, max=10)
    private Integer rightNum;
}
