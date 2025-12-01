package spring.umc.domain.mission.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.umc.domain.mission.converter.MissionConverter;
import spring.umc.domain.mission.converter.UserMissionConverter;
import spring.umc.domain.mission.dto.MissionResDTO;
import spring.umc.domain.mission.dto.UserMissionResDTO;
import spring.umc.domain.mission.entity.Mission;
import spring.umc.domain.mission.entity.mapping.UserMission;
import spring.umc.domain.mission.repository.MissionRepository;
import spring.umc.domain.mission.repository.UserMissionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<UserMissionResDTO.OngoingMissionSummary> getOngoingMissions(Long memberId, int page) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.ASC, "mission.deadline"));

        Page<UserMission> userMissionPage =
                userMissionRepository.findByMember_IdAndIsCompleteFalseOrderByMission_DeadlineAsc(memberId, pageable);

        List<UserMissionResDTO.OngoingMissionSummary> content =
                UserMissionConverter.toOngoingSummaryList(userMissionPage.getContent());

        return new PageImpl<>(content, pageable, userMissionPage.getTotalElements());
    }
    @Override
    @Transactional(readOnly = true)
    public Page<MissionResDTO.StoreMissionSummary> getStoreMissions(Long storeId, int page) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.ASC, "deadline"));

        Page<Mission> missionPage = missionRepository.findByStore_IdOrderByDeadlineAsc(storeId, pageable);

        List<MissionResDTO.StoreMissionSummary> content =
                MissionConverter.toStoreMissionSummaryList(missionPage.getContent());

        return new PageImpl<>(content, pageable, missionPage.getTotalElements());
    }
}
