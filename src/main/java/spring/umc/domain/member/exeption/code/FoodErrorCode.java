package spring.umc.domain.member.exeption.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import spring.umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum FoodErrorCode  implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "FOOD_404_1", "존재하지 않는 음식입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
