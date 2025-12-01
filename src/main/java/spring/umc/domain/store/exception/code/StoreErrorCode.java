package spring.umc.domain.store.exception.code;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import spring.umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum StoreErrorCode implements BaseErrorCode {


    STORE_NOT_FOUND(HttpStatus.BAD_REQUEST, "STORE6001", "해당 가게가 존재하지 않습니다"),
    STORE_QUERY_ERROR(HttpStatus.BAD_REQUEST, "STORE6002", "입력값 오류");
   // NOT_FOUND(HttpStatus.NOT_FOUND, "STORE6003", "해당 가게를 찾을 수 없습니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;
}