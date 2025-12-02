package spring.umc.domain.mission.service;

import org.springframework.data.domain.Page;
import spring.umc.domain.mission.dto.MissionReqDTO;
import spring.umc.domain.mission.dto.MissionResDTO;

import java.util.List;

public interface MissionCommandService {
    MissionResDTO.AddResultDTO addMission(Long storeId, MissionReqDTO.AddMissionDTO request);
    MissionResDTO.ChallengeResultDTO challengeMission(Long userId, Long missionId);
    MissionResDTO.ChallengeResultDTO completeMission(Long userId, Long missionId);
    Page<MissionResDTO.UserMissionPreviewDTO> getInProgressMissions(Integer page);
}