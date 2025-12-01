package spring.umc.domain.review.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class ReviewResDTO {
    @Builder
    public record CreateDTO(
            Long reviewId,
            Long memberId,
            Long storeId,
            Integer rating,
            String content,
            LocalDateTime createdAt
    ) {}

    @Getter
    @Builder
    public static class Summary {
        private Long id;
        private String storeName;
        private String locationName;
        private Integer star;
        private String content;
        private String memberNickname;
        private LocalDateTime createdAt;
    }
}
