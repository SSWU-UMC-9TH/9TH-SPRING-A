package spring.umc.domain.review.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ReviewResDTO {
    private Long id;
    private String content;
    private Integer rating;
    private String storeName;

    @QueryProjection //QReviewResponseDto 생성을 위해 꼭 있어야한다
    public ReviewResDTO(Long id, String content, Integer rating, String storeName) {
        this.id = id;
        this.content = content;
        this.rating = rating;
        this.storeName = storeName;
    }

    @Builder
    public record CreateDTO(
            Long reviewId,
            Long storeId,
            Long userId
    ) {}

}
