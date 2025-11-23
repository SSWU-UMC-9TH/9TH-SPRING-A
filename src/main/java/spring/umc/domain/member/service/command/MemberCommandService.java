package spring.umc.domain.member.service.command;

import spring.umc.domain.member.dto.MemberReqDTO;
import spring.umc.domain.member.dto.MemberResDTO;

public interface MemberCommandService {
    MemberResDTO.JoinDTO signup(
            MemberReqDTO.JoinDTO dto
    );
}
