package spring.umc.domain.member.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import spring.umc.global.apiPayload.code.BaseErrorCode; // 다경이 프로젝트 경로 확인!

@Getter
@AllArgsConstructor
public enum FoodErrorCode implements BaseErrorCode {

    // 음식 카테고리를 못 찾았을 때
    FOOD_NOT_FOUND(HttpStatus.NOT_FOUND,
            "FOOD404_1",
            "해당 음식 카테고리를 찾지 못했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}