package spring.umc.domain.mission.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import spring.umc.global.apiPayload.code.BaseErrorCode;

@Getter
@RequiredArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_1", "존재하지 않는 미션입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER404_1", "존재하지 않는 사용자입니다."),
    ALREADY_IN_PROGRESS(HttpStatus.BAD_REQUEST, "MISSION400_1", "이미 도전 중인 미션입니다."),
    ALREADY_COMPLETED(HttpStatus.BAD_REQUEST, "MISSION400_2", "이미 완료한 미션은 다시 도전할 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
