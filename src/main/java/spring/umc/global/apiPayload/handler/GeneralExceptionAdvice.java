package spring.umc.global.apiPayload.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import spring.umc.global.apiPayload.ApiResponse;
import spring.umc.global.apiPayload.code.BaseErrorCode;
import spring.umc.global.apiPayload.code.GeneralErrorCode;
import spring.umc.global.apiPayload.exception.GeneralException;

@Slf4j
@RestControllerAdvice //Exception을 JSON 형식으로 처리
public class GeneralExceptionAdvice {

    // 애플리케이션에서 발생하는 커스텀 예외 처리
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse<String>> handleException(GeneralException ex) {

        BaseErrorCode code = ex.getCode();

        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(
                        code,               // 에러 코드
                        code.getMessage()   // 커스텀 예외 메시지 노출 (null 금지)
                ));
    }

    // 그 외 정의되지 않은 모든 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(Exception ex) {

        BaseErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR; // 500

        // 내부 로그로만 남김 (외부로 노출 X)
        log.error("[500 ERROR] {}", ex.getMessage(), ex);

        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(
                        code,
                        "서버 내부 오류가 발생했습니다."  // 안전한 메시지로 대체
                ));
    }
}
