package spring.umc.domain.mission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.umc.domain.member.entity.Member;
import spring.umc.domain.mission.entity.Mission;
import spring.umc.domain.mission.entity.mapping.UserMission;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    boolean existsByMemberAndMission(Member member, Mission mission);
}
