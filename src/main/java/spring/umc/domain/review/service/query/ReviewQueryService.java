package spring.umc.domain.review.service.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import spring.umc.domain.review.dto.ReviewResDTO;

public interface ReviewQueryService {
    Page<ReviewResDTO> getMyReviews(Long userId, String storeName, Integer ratingGroup, Pageable pageable);
}
