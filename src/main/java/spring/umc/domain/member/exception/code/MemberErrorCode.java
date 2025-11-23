package spring.umc.domain.member.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import spring.umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    // 회원가입 중복 오류
    DUPLICATED_LOGIN_ID(HttpStatus.BAD_REQUEST, "MEMBER400_1", "이미 사용 중인 로그인 아이디입니다."),
    DUPLICATED_EMAIL(HttpStatus.BAD_REQUEST, "MEMBER400_2", "이미 사용 중인 이메일입니다."),
    DUPLICATED_NICKNAME(HttpStatus.BAD_REQUEST, "MEMBER400_3", "이미 사용 중인 닉네임입니다."),

    // 유저 관련
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_2", "존재하지 않는 회원입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
