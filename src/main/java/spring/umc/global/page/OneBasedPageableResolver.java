package spring.umc.global.page;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.context.request.NativeWebRequest;
import spring.umc.global.annotation.OneBasedPageable;

import java.util.Optional;

@Slf4j
@Component
public class OneBasedPageableResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(OneBasedPageable.class)
                && parameter.getParameterType().equals(Pageable.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, org.springframework.web.bind.support.WebDataBinderFactory binderFactory) {

        int page = Optional.ofNullable(webRequest.getParameter("page"))
                .map(Integer::parseInt)
                .orElseThrow(() -> new IllegalArgumentException("page 값은 필수입니다. (1 이상)"));

        if (page < 1)
            throw new IllegalArgumentException("page 값은 1 이상이어야 합니다.");

        int zeroBase = page - 1;
        return PageRequest.of(zeroBase, 10);
    }
}
