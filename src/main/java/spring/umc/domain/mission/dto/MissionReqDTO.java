package spring.umc.domain.mission.dto;

public class MissionReqDTO {

    public record ChallengeDTO(
            Long userId,
            Long missionId
    ) {}
}
