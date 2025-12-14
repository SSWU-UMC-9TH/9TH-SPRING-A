package spring.umc.domain.mission.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import spring.umc.domain.member.entity.Member;
import spring.umc.domain.mission.entity.Mission;
import spring.umc.domain.mission.entity.mapping.UserMission;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    boolean existsByMemberAndMission(Member member, Mission mission);

    Page<UserMission> findByMember_IdAndIsCompleteFalseOrderByMission_DeadlineAsc(
            Long memberId,
            Pageable pageable
    );
}
