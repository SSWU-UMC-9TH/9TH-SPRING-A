package spring.umc.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralErrorCode implements BaseErrorCode{

    BAD_REQUEST(HttpStatus.BAD_REQUEST,
            "COMMON400_1",
            "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED,
            "AUTH401_1",
            "인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN,
            "AUTH403_1",
            "요청이 거부되었습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND,
            "COMMON404_1",
            "요청한 리소스를 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,
            "COMMON500_1",
            "서버 내부 오류가 발생했습니다."),
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND,
            "STORE4001", "해당 가게를 찾을 수 없습니다."),
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MISSION2001", "해당 미션을 찾을 수 없습니다."),
    MISSION_ALREADY_CHALLENGING(HttpStatus.NOT_FOUND,
            "MISSION2002", "이미 미션을 도전중입니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MEMBER5002", "해당 멤버를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
