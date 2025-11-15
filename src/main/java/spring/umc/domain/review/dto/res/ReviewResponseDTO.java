package spring.umc.domain.review.dto.res;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResponseDTO {

    /**
     * 리뷰 생성
     */
    @Builder
    @Getter
    public static class CreateReviewResultDTO {
        private Long reviewId;
        private String memberName;
        private String storeName;
        private LocalDateTime createdAt;
    }

    /**
     * 리뷰 조회
     */
    @Builder
    @Getter
    public static class ReviewDTO {
        private Long reviewId;
        private String memberName;
        private String storeName;
        private Double score;
        private String content;
        private String reviewImageUrl;
        private LocalDateTime createdAt;
    }

    /**
     * 리뷰 목록 (Page) 조회
     */
    @Builder
    @Getter
    public static class ReviewPageListDTO {
        private List<ReviewDTO> reviewList;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }
}