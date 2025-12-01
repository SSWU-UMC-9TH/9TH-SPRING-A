package spring.umc.domain.member.dto;

import lombok.Builder;
import java.time.LocalDateTime;

public class MemberResDTO {

    @Builder
    public record JoinDTO(
            Long memberId,
            LocalDateTime createdAt
    ) {} // 회원가입

    @Builder
    public record LoginDTO(
            Long memberId,
            String nickname,
            String email
    ) {}//로그인
}
