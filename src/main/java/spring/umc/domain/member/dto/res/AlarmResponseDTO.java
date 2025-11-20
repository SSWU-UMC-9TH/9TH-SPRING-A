package spring.umc.domain.member.dto.res;

import lombok.Builder;
import lombok.Getter;
import spring.umc.domain.member.enums.Dtype;

import java.time.LocalDateTime;
import java.util.List;

public class AlarmResponseDTO {

    /**
     * 알람 조회
     */
    @Builder
    @Getter
    public static class AlarmDTO {
        private Long alarmId;
        private String title;
        private String content;
        private Dtype dtype;
        private LocalDateTime createdAt;
    }

    /**
     * 알람 목록 (Page) 조회
     */
    @Builder
    @Getter
    public static class AlarmPageListDTO {
        private List<AlarmDTO> alarmList;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }
}