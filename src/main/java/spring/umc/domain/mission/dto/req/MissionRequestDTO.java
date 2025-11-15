package spring.umc.domain.mission.dto.req;

import lombok.Getter;
import java.time.LocalDate;

public class MissionRequestDTO {

    @Getter
    public static class CreateMissionDTO {
        private String content;
        private Integer point;
        private LocalDate deadline;
        private String imageUrl;
    }
}