package spring.umc.domain.member.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import spring.umc.global.apiPayload.code.BaseErrorCode;

/**
 * PreferFood 로직(MemberService)에서 사용
 */
@Getter
@AllArgsConstructor
public enum FoodCategoryErrorCode implements BaseErrorCode {

    FOOD_CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "FOOD4001", "존재하지 않는 음식 카테고리 ID입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}