package spring.umc.domain.store.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import spring.umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum StoreErrorCode implements BaseErrorCode {

    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE4001", "존재하지 않는 가게입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}