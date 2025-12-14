package spring.umc.global.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import spring.umc.global.annotation.ValidPage;

@Component
public class PageValidator implements ConstraintValidator<ValidPage, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        // null이면 @RequestParam(defaultValue = "1") 로 채워질 거라 여기선 true 리턴
        if (value == null) return true;

        boolean isValid = value >= 1;

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("page는 1 이상이어야 합니다.")
                    .addConstraintViolation();
        }

        return isValid;
    }
}
