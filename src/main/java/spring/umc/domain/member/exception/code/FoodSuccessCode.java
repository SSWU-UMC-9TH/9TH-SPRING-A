package spring.umc.domain.member.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import spring.umc.global.apiPayload.code.BaseSuccessCode; // 다경이 프로젝트 경로 확인!

@Getter
@AllArgsConstructor
public enum FoodSuccessCode implements BaseSuccessCode {

    // 음식 카테고리 조회 성공
    FOOD_CATEGORY_FOUND(HttpStatus.OK,
            "FOOD200_1",
            "성공적으로 음식 카테고리를 조회했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}