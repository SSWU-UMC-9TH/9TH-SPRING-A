package spring.umc.global.annotation;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)      // 메서드 파라미터에 적용
@Retention(RetentionPolicy.RUNTIME) // 런타임 적용
@Documented
public @interface OneBasedPageable {}
