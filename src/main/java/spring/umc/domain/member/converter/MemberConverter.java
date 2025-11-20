package spring.umc.domain.member.converter;

import org.springframework.data.domain.Page;
import spring.umc.domain.member.dto.req.MemberRequestDTO;
import spring.umc.domain.member.dto.res.MemberResponseDTO;
import spring.umc.domain.member.entity.Member;

import java.util.List;
import java.util.stream.Collectors;

public class MemberConverter {

    /**
     * Entity -> DTO (단일)
     */
    public static MemberResponseDTO.MemberDTO toMemberDTO(Member member) {
        return MemberResponseDTO.MemberDTO.builder()
                .memberId(member.getMemberId())
                .name(member.getName())
                .gender(member.getGender())
                .email(member.getEmail())
                .address(member.getAddress())
                .status(member.getStatus())
                .point(member.getPoint())
                .build();
    }

    /**
     * Page<Entity> -> DTO (목록)
     */
    public static MemberResponseDTO.MemberPageListDTO toMemberPageListDTO(Page<Member> memberPage) {
        List<MemberResponseDTO.MemberDTO> memberList = memberPage.getContent().stream()
                .map(MemberConverter::toMemberDTO)
                .collect(Collectors.toList());

        return MemberResponseDTO.MemberPageListDTO.builder()
                .memberList(memberList)
                .listSize(memberList.size())
                .totalPage(memberPage.getTotalPages())
                .totalElements(memberPage.getTotalElements())
                .isFirst(memberPage.isFirst())
                .isLast(memberPage.isLast())
                .build();
    }

    /**
     * 회원가입
     */
    // Entity -> DTO
    public static MemberResponseDTO.JoinDTO toJoinDTO(
            Member member
    ){
        return MemberResponseDTO.JoinDTO.builder()
                .memberId(member.getMemberId())
                .createAt(member.getCreatedAt())
                .build();
    }

    // DTO -> Entity
    public static Member toMember(
            MemberRequestDTO.JoinDTO dto
    ){
        return Member.builder()
                .name(dto.name())
                .gender(dto.gender())
                .age(dto.age())
                .email(dto.email())
                .address(dto.address())
                .phoneNumber(dto.phoneNumber())
                .build();
    }
}