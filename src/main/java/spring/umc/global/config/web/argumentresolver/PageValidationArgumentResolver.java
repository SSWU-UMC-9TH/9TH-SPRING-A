package spring.umc.global.config.web.argumentresolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import spring.umc.global.annotation.CheckPage;
import spring.umc.global.apiPayload.exception.PageNumberException;
// 필요한 import 생략

public class PageValidationArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 파라미터에 @CheckPage 어노테이션이 붙어 있는지 확인
        return parameter.hasParameterAnnotation(CheckPage.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        // 1. "page" 쿼리 스트링 값 가져오기
        String pageValue = webRequest.getParameter("page");

        if (pageValue == null) {
            // 값이 없으면 그냥 1페이지로 기본값 설정하거나 예외 처리
            return 1; // 기본값 1 페이지로 처리
        }

        try {
            int page = Integer.parseInt(pageValue);

            // 2. 핵심 검증 로직: 프론트엔드에서 1 이상의 page를 전달해야 함
            if (page < 1) { //0부터되는거같음.뭐지
                // page가 1 미만이면 커스텀 예외 발생! (RestControllerAdvice로 넘어감)
                throw new PageNumberException("페이지 번호는 1 이상이어야 합니다. 현재 페이지: " + page);
            }
            return page;

        } catch (NumberFormatException e) {
            // 숫자가 아닌 이상한 문자열이 들어왔을 때
            throw new PageNumberException("페이지 번호는 숫자 형식이어야 합니다.");
        }
    }
}