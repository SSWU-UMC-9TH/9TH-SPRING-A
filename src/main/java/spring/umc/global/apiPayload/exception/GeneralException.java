package spring.umc.global.apiPayload.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import spring.umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor //자동으로 생성자 생성해준다
public class GeneralException extends RuntimeException {
    private final BaseErrorCode code;
}//GeneralException은 GeneralExceptionAdvice 에서 감지
