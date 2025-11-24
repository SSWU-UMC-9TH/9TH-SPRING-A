package spring.umc.domain.mission.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

public class MissionReqDTO {

    @Getter
    public static class AddMissionDTO {
        @NotNull
        @Min(10)
        private Integer pointReward;

        @NotNull
        @Future
        private LocalDate deadline;

        @NotBlank
        private String missionCondition;
    }
}
