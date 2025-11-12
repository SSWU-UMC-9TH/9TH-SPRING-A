package spring.umc.domain.mission.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.umc.domain.member.entity.Member;
import spring.umc.domain.member.enums.Status;
import spring.umc.domain.mission.entity.Mission;
import spring.umc.domain.mission.entity.mapping.MemberMission;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long>, MemberMissionRepositoryCustom {

    // 1. 쿼리 메서드

    /**
     * 특정 사용자(Member)와 특정 상태(Status)에 있는 모든 미션 매핑 찾기
     */
    List<MemberMission> findByMemberAndStatus(Member member, Status status);

    /**
     * 특정 사용자와 특정 미션의 매핑 정보 찾기
     */
    Optional<MemberMission> findByMemberAndMission(Member member, Mission mission);

    // 2. @Query 어노테이션 (JOIN 활용)

    /**
     * 특정 사용자(Member)가 '도전 중(CHALLENGING)'인 미션(Mission) 목록 반환
     */
//    @Query("SELECT mm.mission FROM MemberMission mm WHERE mm.member = :member AND mm.status = 'CHALLENGING'")
//    List<Mission> findChallengingMissionsByMember(@Param("member") Member member);

    /**
     * 특정 사용자(Member)가 '도전 중(CHALLENGING)'인 미션(Mission) 목록 반환
     * (JPQL 및 페이징 수정)
     */
    @Query("SELECT mm.mission FROM MemberMission mm " +
            "WHERE mm.member = :member " +
            "AND mm.status = spring.umc.domain.mission.enums.Status.CHALLENGING")
    Page<Mission> findChallengingMissionsByMember(@Param("member") Member member, Pageable pageable);
}