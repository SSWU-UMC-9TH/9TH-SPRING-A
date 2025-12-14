package spring.umc.domain.mission.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

public class MissionResDTO {

    @Getter
    @Builder
    public static class StoreMissionSummary {
        private Long missionId;
        private String storeName;
        private String conditional;
        private Integer point;
        private LocalDate deadline;
    }
}
