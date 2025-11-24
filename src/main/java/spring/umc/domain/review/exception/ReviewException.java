package spring.umc.domain.review.exception;

import lombok.Getter;
import spring.umc.global.apiPayload.exception.GeneralException;
import spring.umc.global.apiPayload.code.BaseErrorCode;

@Getter
public class ReviewException extends GeneralException {
    public ReviewException(BaseErrorCode code) {
        super(code);
    }
}
