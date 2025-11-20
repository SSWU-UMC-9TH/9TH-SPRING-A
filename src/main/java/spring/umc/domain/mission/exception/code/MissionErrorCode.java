package spring.umc.domain.mission.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import spring.umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION4001", "존재하지 않는 미션입니다."),

    MEMBER_MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION4002", "해당 회원의 미션 도전 정보를 찾을 수 없습니다."),
    MEMBER_MISSION_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "MISSION4003", "이미 도전 중인 미션입니다."),
    MEMBER_MISSION_NOT_CHALLENGING(HttpStatus.BAD_REQUEST, "MISSION4004", "미션의 상태가 '도전 중'이 아닙니다. (이미 완료했거나 포기함)");


    private final HttpStatus status;
    private final String code;
    private final String message;
}