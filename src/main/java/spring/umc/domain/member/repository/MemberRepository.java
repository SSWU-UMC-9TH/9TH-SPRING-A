package spring.umc.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.umc.domain.member.dto.MyPageInfoDTO;
import spring.umc.domain.member.entity.Member;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {


    @Query("SELECT new spring.umc.domain.member.dto.MyPageInfoDTO(m.name, m.email, m.phoneNum, m.id, COALESCE(SUM(ph.point), 0)) " +
            //  COALESCE(값, 0)  값이 Null이면 0을 줘라! 라는 뜻
            "FROM Member m " +
            "LEFT JOIN m.pointHistoryList ph " +
            "WHERE m.id = :userId " +
            "GROUP BY m.id, m.name, m.email, m.phoneNum")
    Optional<MyPageInfoDTO> findMyPageInfoByUserId(@Param("userId") Long userId);
}