package spring.umc.domain.member.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import spring.umc.domain.member.dto.req.MemberRequestDTO;
import spring.umc.domain.member.dto.res.MemberResponseDTO;
import spring.umc.domain.member.entity.Member;
import spring.umc.domain.member.enums.Gender;
import spring.umc.domain.member.enums.Status;

public interface MemberService {

    // Member ID로 조회
    Member findMemberById(Long memberId);

    // Member 동적 쿼리 (QueryDSL) 호출
    Page<Member> searchMembers(
            String nameKeyword,
            Gender gender,
            Status status,
            Pageable pageable
    );

    /**
     * 선호 음식 변경
     */
    void updatePreferFoods(Long memberId, List<Long> categoryIds);

    /**
     * 회원가입
     */
    MemberResponseDTO.JoinDTO signup(MemberRequestDTO.JoinDTO dto);
}