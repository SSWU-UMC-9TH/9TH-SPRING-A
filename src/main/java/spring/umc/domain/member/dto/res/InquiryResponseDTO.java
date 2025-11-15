package spring.umc.domain.member.dto.res;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.List;

public class InquiryResponseDTO {

    /**
     * 문의 생성
     */
    @Builder
    @Getter
    public static class CreateInquiryResultDTO {
        private Long inquiryId;
        private String memberName;
        private String title;
        private LocalDateTime createdAt;
    }

    /**
     * 문의 조회
     */
    @Builder
    @Getter
    public static class InquiryDTO {
        private Long inquiryId;
        private String title;
        private String content;
        private String inquiryImageUrl;
        private LocalDateTime createdAt;
    }

    /**
     * 문의 목록 (Page) 조회
     */
    @Builder
    @Getter
    public static class InquiryPageListDTO {
        private List<InquiryDTO> inquiryList;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }
}