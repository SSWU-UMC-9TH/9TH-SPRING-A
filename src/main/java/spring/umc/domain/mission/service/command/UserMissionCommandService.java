package spring.umc.domain.mission.service.command;

import spring.umc.domain.mission.dto.UserMissionReqDTO;
import spring.umc.domain.mission.dto.UserMissionResDTO;

public interface UserMissionCommandService {
    UserMissionResDTO.ChallengeDTO challengeMission(
            Long userId,
            UserMissionReqDTO.ChallengeDTO dto
    );
}
