package spring.umc.domain.member.converter;

import spring.umc.domain.member.dto.MemberReqDTO;
import spring.umc.domain.member.dto.MemberResDTO;
import spring.umc.domain.member.entity.Member;
import spring.umc.global.auth.enums.Role;

public class MemberConverter {

    // Entity -> DTO
    public static MemberResDTO.JoinDTO toJoinDTO(
            Member member
    ){
        return MemberResDTO.JoinDTO.builder()
                .memberId(member.getId())
                .createAt(member.getCreatedAt())
                .build();
    }

    // DTO -> Entity
    public static Member toMember(
            MemberReqDTO.JoinDTO dto,
            String password,
            Role role
    ){
        return Member.builder()
                .name(dto.name())
                .email(dto.email())
                .password(password)
                .role(role)
                .birth(dto.birth())
                .address(dto.address())
                .detailAddress(dto.specAddress())
                .gender(dto.gender())
                .build();
    }

    // 로그인 응답 DTO 변환 (추가할 부분!)
    public static MemberResDTO.LoginDTO toLoginDTO(Member member, String accessToken) {
        return MemberResDTO.LoginDTO.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .accessToken(accessToken)
                .build();
    }
}