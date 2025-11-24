package spring.umc.domain.member.exception;

import spring.umc.domain.member.exception.code.FoodCategoryErrorCode;
import spring.umc.global.apiPayload.exception.GeneralException;

public class MemberException extends GeneralException {
    public MemberException(FoodCategoryErrorCode code) {
        super(code);
    }
}