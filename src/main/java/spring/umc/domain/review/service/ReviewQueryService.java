package spring.umc.domain.review.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import spring.umc.domain.review.dto.ReviewReqDTO;
import spring.umc.domain.review.dto.ReviewResponseDTO;
import spring.umc.domain.review.entity.Review;
import java.util.List;


public interface ReviewQueryService {


    List<Review> searchReview(String type, String query);
   // ReviewResponseDTO.ReviewPreViewListDTO getReviews();
   public Page<Review> searchMyReview(Long memberId, Integer pageable) ;
   ReviewResponseDTO.CreateReviewResultDto addReview(Long userId, Long storeId, ReviewReqDTO.AddReviewDTO request);

    ReviewResponseDTO.ReviewPreViewListDTO findReview(
            String storeName, Integer page
    );
}