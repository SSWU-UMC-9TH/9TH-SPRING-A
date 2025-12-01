package spring.umc.domain.review.exeption;

import spring.umc.domain.review.exeption.code.ReviewErrorCode;
import spring.umc.global.apiPayload.exception.GeneralException;

public class ReviewException  extends GeneralException {

    public ReviewException(ReviewErrorCode errorCode) {
        super(errorCode);
    }
}
