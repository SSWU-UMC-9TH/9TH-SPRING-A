package spring.umc.domain.review.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    @Builder
    public record ReviewPreViewListDTO(
            List<ReviewPreViewDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record ReviewPreViewDTO(
            String ownerNickname,
            Float score,
            String body,
            LocalDate createdAt
    ){}
}
