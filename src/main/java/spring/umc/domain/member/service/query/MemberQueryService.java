package spring.umc.domain.member.service.query;

import spring.umc.domain.member.dto.MemberReqDTO;

public interface MemberQueryService {
    Object login(MemberReqDTO.LoginDTO dto);
}
