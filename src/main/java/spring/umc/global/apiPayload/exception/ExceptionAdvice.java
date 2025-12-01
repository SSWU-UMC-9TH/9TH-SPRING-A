package spring.umc.global.apiPayload.exception; // 패키지 경로는 다경이 프로젝트에 맞게!

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import spring.umc.global.apiPayload.ApiResponse;
import spring.umc.global.apiPayload.code.GeneralErrorCode; // 🚨 GeneralErrorCode 대신 ErrorStatus를 쓴다면 여기 수정!

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static spring.umc.global.apiPayload.code.GeneralErrorCode.PAGE_NUMBER_INVALID;

@Slf4j
@RestControllerAdvice(annotations = {RestController.class})
public class ExceptionAdvice {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        Map<String, String> errors = new LinkedHashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = Optional.ofNullable(error.getDefaultMessage()).orElse("");
            errors.merge(fieldName, errorMessage, (existingErrorMessage, newErrorMessage) -> existingErrorMessage + ", " + newErrorMessage);
        });


        GeneralErrorCode code = GeneralErrorCode.BAD_REQUEST;

        return ResponseEntity
                .status(code.getStatus())
                .body(ApiResponse.onFailure(code,errors));
    }

    @ExceptionHandler(PageNumberException.class)
    public ApiResponse<String> handlePageNumberException(PageNumberException e) {
        // 400 BAD REQUEST 상태 코드를 사용하고, 메시지를 반환
        return ApiResponse.onFailure(PAGE_NUMBER_INVALID, e.getMessage());
    }
}