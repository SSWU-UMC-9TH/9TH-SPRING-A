package spring.umc.domain.member.exeption;

import spring.umc.domain.member.exeption.code.FoodErrorCode;
import spring.umc.global.apiPayload.exception.GeneralException;

public class FoodException extends GeneralException {

    public FoodException(FoodErrorCode errorCode) {
        super(errorCode); // GeneralException이 BaseErrorCode를 받는 생성자가 있다고 가정
    }
}
