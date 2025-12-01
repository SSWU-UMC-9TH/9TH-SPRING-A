package spring.umc.domain.member.converter;


import spring.umc.domain.member.dto.MemberReqDTO;
import spring.umc.domain.member.dto.MemberResDTO;
import spring.umc.domain.member.entity.User;

public class MemberConverter {

    // 회원가입 요청 DTO → User Entity
    public static User toUser(MemberReqDTO.JoinDTO dto) {
        return User.builder()
                .loginId(dto.loginId())
                .name(dto.name())
                .nickname(dto.nickname())
                .email(dto.email())
                .password(dto.password())
                .introduction(dto.introduction())
                .build();
    }

    // User Entity → 회원가입 응답 DTO
    public static MemberResDTO.JoinDTO toJoinDTO(User user) {
        return MemberResDTO.JoinDTO.builder()
                .memberId(user.getId())
                .createdAt(user.getCreatedAt())
                .build();
    }

    // User Entity → 로그인 응답 DTO
    public static MemberResDTO.LoginDTO toLoginDTO(User user) {
        return MemberResDTO.LoginDTO.builder()
                .memberId(user.getId())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .build();
    }
}
