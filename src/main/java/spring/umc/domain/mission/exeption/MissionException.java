package spring.umc.domain.mission.exeption;

import spring.umc.domain.mission.exeption.code.MissionErrorCode;
import spring.umc.global.apiPayload.exception.GeneralException;

public class MissionException extends GeneralException {

    public MissionException(MissionErrorCode errorCode) {
        super(errorCode);
    }
}
