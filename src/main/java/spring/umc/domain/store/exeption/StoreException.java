package spring.umc.domain.store.exeption;

import spring.umc.domain.store.exeption.code.StoreErrorCode;
import spring.umc.global.apiPayload.exception.GeneralException;

public class StoreException extends GeneralException {

    public StoreException(StoreErrorCode errorCode) {
        super(errorCode);
    }
}
