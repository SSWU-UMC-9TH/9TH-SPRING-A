package spring.umc.domain.review.repository;

import com.querydsl.core.types.Predicate;
import spring.umc.domain.review.entity.Review;

import java.util.List;

public interface ReviewQueryDsl {
    List<Review> searchReview(
            Predicate predicate
    );
    List<Review> searchMyReview(
            Predicate predicate
    );
}
