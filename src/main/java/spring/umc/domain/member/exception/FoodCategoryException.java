package spring.umc.domain.member.exception;

import spring.umc.global.apiPayload.code.BaseErrorCode;
import spring.umc.global.apiPayload.exception.GeneralException;

/**
 * PreferFood 로직(MemberService)에서 사용
 */
public class FoodCategoryException extends GeneralException {

    public FoodCategoryException(BaseErrorCode code) {
        super(code);
    }
}