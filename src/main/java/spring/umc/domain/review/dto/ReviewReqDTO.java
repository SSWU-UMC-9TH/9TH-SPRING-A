package spring.umc.domain.review.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class ReviewReqDTO {

    @Getter
    public static class AddReviewDTO {

        @NotNull
        private Float star;

        @NotBlank
        private String content;
    }
}