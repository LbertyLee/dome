package com.lh.dome.common.validate.validator;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

/**
 * 空列表
 *
 * @  lihong
 * @date 2023/04/19
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmptyListValidator.class)
public @interface EmptyList {
    String message() default "数组不能为空数组";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};



}
/**
 * 非空数组验证器
 *
 * @  lihong
 * @date 2023/04/19
 */
class EmptyListValidator implements ConstraintValidator<EmptyList, List<?>> {
    @Override
    public boolean isValid(List<?> value, ConstraintValidatorContext context) {
        if(value ==null){
            return true;
        }
        return value != null &&!value.isEmpty();


    }
}



