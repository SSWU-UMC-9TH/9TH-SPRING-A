package spring.umc.domain.mission.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import spring.umc.domain.mission.entity.Mission;
import spring.umc.domain.mission.entity.mapping.UserMission;
import spring.umc.domain.store.entity.Location;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    Page<Mission> findByStore_IdOrderByDeadlineAsc(Long storeId, Pageable pageable);

//    Page<UserMission> findByMember_IdAndIsCompleteFalseOrderByMission_DeadlineDesc(
//            Long userId, Pageable pageable);
//
//    // 완료 목록: 미션 마감일 DESC (원래는 doneDate DESC였으나 현재 필드가 없음)
//    Page<UserMission> findByMember_IdAndIsCompleteTrueOrderByMission_DeadlineDesc(
//            Long userId, Pageable pageable);
//
//    // 홈 - 진행중 + 주소 키워드: 미션 마감일 DESC
//    Page<UserMission> findByMember_IdAndIsCompleteFalseAndMission_Store_Location_NameContainingOrderByMission_DeadlineDesc(
//            Long userId, Location name, Pageable pageable
//    );
//    // 홈 - 완료 미션 갯수
//    long countByMember_IdAndIsCompleteTrue(Long userId);
}
