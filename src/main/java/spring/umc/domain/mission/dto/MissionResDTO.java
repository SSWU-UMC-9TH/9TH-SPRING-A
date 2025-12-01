package spring.umc.domain.mission.dto;

import lombok.Builder;

public class MissionResDTO {

    @Builder
    public record ChallengeDTO(
            Long userMissionId,
            Long userId,
            Long missionId,
            String status
    ) {}
}
