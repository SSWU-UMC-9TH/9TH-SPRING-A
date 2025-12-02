package spring.umc.domain.member.exception;

import spring.umc.global.apiPayload.code.BaseErrorCode;
import spring.umc.global.apiPayload.exception.GeneralException; // 아까 만든 그거!

public class FoodException extends GeneralException {

      public FoodException(BaseErrorCode code) {
        super(code);
    }
}