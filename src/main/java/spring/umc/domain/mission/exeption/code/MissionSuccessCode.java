package spring.umc.domain.mission.exeption.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import spring.umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    CHALLENGED(HttpStatus.CREATED,
            "MISSION201_1",
            "미션 도전에 성공했습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
