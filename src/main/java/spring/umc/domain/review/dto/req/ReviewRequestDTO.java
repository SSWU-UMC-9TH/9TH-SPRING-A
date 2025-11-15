package spring.umc.domain.review.dto.req;

import lombok.Getter;

public class ReviewRequestDTO {

    @Getter
    public static class CreateReviewDTO {
        private String content;
        private Double score;
        private String imageUrl;
    }
}