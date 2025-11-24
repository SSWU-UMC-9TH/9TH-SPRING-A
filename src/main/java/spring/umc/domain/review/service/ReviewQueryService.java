package spring.umc.domain.review.service;

import spring.umc.domain.review.dto.ReviewReqDTO;
import spring.umc.domain.review.dto.ReviewResponseDTO;
import spring.umc.domain.review.entity.Review;
import java.util.List;


public interface ReviewQueryService {


    List<Review> searchReview(String type, String query);

    List<Review> searchMyReview(Long memberId, String type, String query);
    ReviewResponseDTO.CreateReviewResultDto addReview(Long userId, Long storeId, ReviewReqDTO.AddReviewDTO request);
}