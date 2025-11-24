package spring.umc.domain.member.exception.code;

import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import spring.umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum FoodCategoryErrorCode implements BaseErrorCode {
    // 카테고리 관련 오류
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_1", "존재하지 않는 음식 카테고리입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

}
