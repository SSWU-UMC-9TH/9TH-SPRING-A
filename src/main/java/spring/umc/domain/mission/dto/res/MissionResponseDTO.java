package spring.umc.domain.mission.dto.res;

import lombok.Builder;
import lombok.Getter;
import spring.umc.domain.mission.enums.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResponseDTO {

    /**
     * 미션 생성
     */
    @Builder
    @Getter
    public static class CreateMissionResultDTO {
        private Long missionId;
        private String storeName;
        private LocalDateTime createdAt;
    }

    /**
     * 미션 도전
     */
    @Builder
    @Getter
    public static class ChallengeMissionResultDTO {
        private Long memberMissionId;
        private String memberName;
        private String missionContent;
        private Status status;
    }

    /**
     * 미션 조회
     */
    @Builder
    @Getter
    public static class MissionDTO {
        private Long missionId;
        private String storeName;
        private String content;
        private Integer point;
        private LocalDate deadline;
    }

    /**
     * 미션 목록 (Page) 조회
     */
    @Builder
    @Getter
    public static class MissionPageListDTO {
        private List<MissionDTO> missionList;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }
}