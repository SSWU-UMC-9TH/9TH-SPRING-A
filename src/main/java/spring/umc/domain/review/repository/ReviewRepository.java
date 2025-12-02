package spring.umc.domain.review.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import spring.umc.domain.review.entity.Review;
import spring.umc.domain.store.entity.Store;

public interface ReviewRepository extends JpaRepository<Review,Long>, ReviewQueryDsl {
    Page<Review> findAllByStore(Store store, Pageable pageable);
    Page<Review> findByMemberId(Long memberId, Pageable pageable);
}
