package spring.umc.domain.mission.exeption.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import spring.umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "MISSION404_1",
            "해당 미션을 찾지 못했습니다."),

    ALREADY_CHALLENGED(HttpStatus.BAD_REQUEST,
            "MISSION400_1",
            "이미 도전 중인 미션입니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
