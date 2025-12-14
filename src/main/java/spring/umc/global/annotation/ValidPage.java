package spring.umc.global.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import spring.umc.global.validator.PageValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PageValidator.class)
@Target(ElementType.PARAMETER)          // 쿼리 스트링 page 파라미터에 붙일 거라 PARAMETER
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPage {

    String message() default "page는 1 이상이어야 합니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
