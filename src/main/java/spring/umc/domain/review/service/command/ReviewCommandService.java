package spring.umc.domain.review.service.command;

import spring.umc.domain.review.dto.ReviewReqDTO;
import spring.umc.domain.review.dto.ReviewResDTO;

public interface ReviewCommandService {
    ReviewResDTO.CreateDTO createReview(
            Long storeId,
            ReviewReqDTO.CreateDTO dto
    );
}
