package com.lh.dome.common.validate.validator;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.regex.Pattern;

/**
 * 文件名称
 *
 * @  lihong
 * @date 2023/04/21
 */
@Target(value = { ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FileNameValidator.class)
public @interface FileName {
    String message() default "名称为系统保留词";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

class FileNameValidator implements ConstraintValidator<FileName, String> {
    private static final Pattern WINDOWS_FILE_NAME_PATTERN = Pattern.compile(
            "^(?!^(?:com[1-9]|lpt[1-9]|con|prn|aux|nul|com[0-9]|lpt[0-9])(?:\\..*)?$)[^<>:\"/\\\\|?*\\x00-\\x1F]+$");

    private String message;

    @Override
    public void initialize(FileName constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            if (StringUtils.isBlank(value)) {
                throw new Exception("文件名称不能为空");
            }
            if (value.length() > 250) {
                throw new Exception("文件名称不可大于250个字符");
            }
            if (!WINDOWS_FILE_NAME_PATTERN.matcher(value.toLowerCase()).matches()) {
                throw new Exception("文件名称不能包含关键字");
            }
            return true;
        } catch (Exception e) {
            message = e.getMessage();
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation().disableDefaultConstraintViolation();
        }
        return false;
    }
}
