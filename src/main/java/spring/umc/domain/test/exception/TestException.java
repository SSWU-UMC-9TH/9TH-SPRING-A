package spring.umc.domain.test.exception;

import spring.umc.global.apiPayload.code.BaseErrorCode;
import spring.umc.global.apiPayload.exception.GeneralException;

public class TestException extends GeneralException {
    public TestException(BaseErrorCode code) {

        super(code); //부모 클래스 GeneralException 생성자를 호출하여 BaseErrorCode 필드 초기화
    }
}
