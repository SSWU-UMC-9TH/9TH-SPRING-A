package spring.umc.domain.member.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import spring.umc.global.apiPayload.code.BaseErrorCode;

/**
 * Member 도메인 관련 Error Code
 */
@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER4001", "존재하지 않는 회원입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}