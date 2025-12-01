package spring.umc.domain.review.repository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import spring.umc.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDsl {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO review (store, user_id, score, contents, created_at) " +
            "VALUES (:storeId, :userId, :score, :contents, NOW())",
            nativeQuery = true)
    int insertReview(@Param("storeId") Long storeId,
                     @Param("userId") Long userId,
                     @Param("score") float score,
                     @Param("contents") String contents);
}
