package spring.umc.domain.mission.dto;

import lombok.Builder;
import java.time.LocalDateTime;

public class MissionResDTO {

    @Builder
    public record ChallengeDTO(
            Long userMissionId,
            Long userId,
            Long missionId,
            String status
    ) {}

    /** 특정 가게의 미션 목록 조회 DTO */
    @Builder
    public record MissionListDTO(
            Long missionId,
            String missionName,
            Integer rewardPoint,
            String storeName
    ) {}

    /** 내가 진행중인 미션 목록 조회 DTO */
    @Builder
    public record MyMissionDTO(
            Long userMissionId,
            String missionName,
            String storeName,
            String status,
            LocalDateTime assignedAt
    ) {}
}
