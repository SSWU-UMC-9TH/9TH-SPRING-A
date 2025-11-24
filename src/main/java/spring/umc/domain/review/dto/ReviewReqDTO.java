package spring.umc.domain.review.dto;

public class ReviewReqDTO {

    public record CreateDTO(
            Long storeId,
            Long userId,
            String content,
            Integer rating
    ) {}
}
