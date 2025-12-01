package spring.umc.domain.member.dto;

import java.util.List;

public class MemberReqDTO {

    // 회원가입 요청 DTO
    public record JoinDTO(
            String loginId,
            String name,
            String nickname,
            String email,
            String password,
            String introduction,
            List<Long> preferCategory
    ) {}

    // 로그인 요청 DTO
    public record LoginDTO(
            String loginId,
            String password
    ) {}

}
