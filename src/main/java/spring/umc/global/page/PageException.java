package spring.umc.global.page;

import spring.umc.global.apiPayload.code.BaseErrorCode;
import spring.umc.global.apiPayload.exception.GeneralException;

public class PageException extends GeneralException {

    public PageException(BaseErrorCode code) {
        super(code);
    }
}
