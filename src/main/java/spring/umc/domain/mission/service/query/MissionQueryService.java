package spring.umc.domain.mission.service.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import spring.umc.domain.mission.dto.MissionResDTO;

public interface MissionQueryService {

    Page<MissionResDTO.MissionListDTO> getStoreMissions(Long storeId, Pageable pageable);

    Page<MissionResDTO.MyMissionDTO> getMyMissions(Long userId, Pageable pageable);
}
