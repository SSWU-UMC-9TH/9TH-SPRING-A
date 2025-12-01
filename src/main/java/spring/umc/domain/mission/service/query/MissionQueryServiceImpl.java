package spring.umc.domain.mission.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.umc.domain.mission.dto.MissionResDTO;
import spring.umc.domain.mission.repository.MemberMissionRepository;
import spring.umc.domain.mission.repository.MissionRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public Page<MissionResDTO.MissionListDTO> getStoreMissions(Long storeId, Pageable pageable){
        return missionRepository.findByStoreId(storeId, pageable)
                .map(m -> MissionResDTO.MissionListDTO.builder()
                        .missionId(m.getId())
                        .missionName(m.getName())
                        .rewardPoint(m.getRewardPoint())
                        .storeName(m.getStore().getName())
                        .build());
    }

    @Override
    public Page<MissionResDTO.MyMissionDTO> getMyMissions(Long userId, Pageable pageable){
        return memberMissionRepository.findAllByUserIdAndStatusInOrderByAssignedAtDesc(userId, pageable)
                .map(um -> MissionResDTO.MyMissionDTO.builder()
                        .userMissionId(um.getId())
                        .missionName(um.getMission().getName())
                        .storeName(um.getMission().getStore().getName())
                        .status(um.getStatus().name())
                        .assignedAt(um.getAssignedAt())
                        .build());
    }
}
