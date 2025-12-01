package spring.umc.domain.mission.dto;

import jakarta.validation.constraints.NotNull;

public class UserMissionReqDTO {
    // 미션 도전 요청 DTO
    public record ChallengeDTO(
            @NotNull(message = "미션 ID는 필수입니다.")
            Long missionId
    ) {}
}
