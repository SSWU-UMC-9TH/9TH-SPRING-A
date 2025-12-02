package spring.umc.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MissionResDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddResultDTO {
        private Long missionId;
        private LocalDateTime createdAt;
    }


    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChallengeResultDTO {
        private Long userMissionId;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CompleteResultDTO {
        private Long userMissionId;
        private LocalDateTime createdAt;
        private Long missionId;         // 추가
        private String missionName;     // 추가
        private String status;          // 추가
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserMissionPreviewDTO {
        private Long userMissionId;
        private Long missionId;
        private String missionName;
        private LocalDateTime dueDate; // 미션 마감일 (Mission 엔티티에 필드가 있다고 가정)
        private String status;         // "IN_PROGRESS"
    }
}