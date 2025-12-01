package spring.umc.domain.review.exeption.code;

import lombok.Getter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import spring.umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode  implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404_1", "해당 리뷰를 찾지 못했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
