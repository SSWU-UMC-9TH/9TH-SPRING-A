package spring.umc.domain.mission.service.query;

import org.springframework.data.domain.Page;
import spring.umc.domain.mission.dto.MissionResDTO;
import spring.umc.domain.mission.dto.UserMissionResDTO;

public interface MissionQueryService {
    Page<MissionResDTO.StoreMissionSummary> getStoreMissions(Long storeId, int page);

    Page<UserMissionResDTO.OngoingMissionSummary> getOngoingMissions(Long memberId, int page);

}
