package spring.umc.domain.review.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import spring.umc.domain.review.entity.Review;

public interface ReviewService {

    /**
     * 리뷰 생성 (비즈니스 로직 + 트랜잭션)
     */
    Review createReview(Long memberId, Long storeId, String content, Double score, String imageUrl);

    /**
     * 리뷰 동적 검색 (QueryDSL)
     */
    Page<Review> searchReviews(
            Long memberId,
            Long storeId,
            Double minScore,
            Pageable pageable
    );
}