package spring.umc.global.page;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import spring.umc.global.apiPayload.code.BaseErrorCode;

@Getter
@RequiredArgsConstructor
public enum PageErrorCode implements BaseErrorCode {

    INVALID_PAGE(HttpStatus.BAD_REQUEST, "PAGE400_1", "page 파라미터는 1 이상의 정수여야 합니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
