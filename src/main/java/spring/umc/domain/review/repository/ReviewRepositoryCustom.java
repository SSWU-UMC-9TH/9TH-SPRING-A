package spring.umc.domain.review.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import spring.umc.domain.review.entity.Review;

public interface ReviewRepositoryCustom {

    Page<Review> searchReviews(
            Long memberId,
            Long storeId,
            Double minScore,
            Pageable pageable
    );
}