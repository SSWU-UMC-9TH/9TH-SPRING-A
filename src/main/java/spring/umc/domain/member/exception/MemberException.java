package spring.umc.domain.member.exception;

import spring.umc.global.apiPayload.code.BaseErrorCode;
import spring.umc.global.apiPayload.exception.GeneralException;

/**
 * Member 도메인 관련 Exception
 */
public class MemberException extends GeneralException {

    public MemberException(BaseErrorCode code) {
        super(code);
    }
}