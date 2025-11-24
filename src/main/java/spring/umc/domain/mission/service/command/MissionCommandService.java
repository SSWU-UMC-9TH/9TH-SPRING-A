package spring.umc.domain.mission.service.command;

import spring.umc.domain.mission.dto.MissionReqDTO;
import spring.umc.domain.mission.dto.MissionResDTO;

public interface MissionCommandService {

    // 미션 도전하기
    MissionResDTO.ChallengeDTO challengeMission(MissionReqDTO.ChallengeDTO dto);
}
