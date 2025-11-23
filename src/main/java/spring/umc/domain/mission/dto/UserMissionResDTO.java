package spring.umc.domain.mission.dto;

import lombok.Builder;

public class UserMissionResDTO {
    @Builder
    public record ChallengeDTO(
            Long userMissionId,
            Long memberId,
            Long missionId,
            Long storeId,
            boolean isComplete
    ) {}
}
