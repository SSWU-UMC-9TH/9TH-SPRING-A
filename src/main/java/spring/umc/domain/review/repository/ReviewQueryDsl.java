package spring.umc.domain.review.repository;

import org.springframework.data.domain.Page;
import spring.umc.domain.review.dto.ReviewResDTO;
import spring.umc.domain.review.entity.Review;

import java.util.List;
import com.querydsl.core.types.Predicate;

public interface ReviewQueryDsl {
    List<Review> searchReview(
            Predicate predicate
    );
    List<Review> mysearchReview(
            Predicate predicate
    );
    Page<ReviewResDTO.Summary> getMyReviews(Long memberId, int page);

}
