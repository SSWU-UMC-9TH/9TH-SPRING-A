package spring.umc.domain.review.service.query;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.umc.domain.member.entity.User;
import spring.umc.domain.member.repository.MemberRepository;
import spring.umc.domain.review.dto.ReviewResDTO;
import spring.umc.domain.review.entity.QReview;
import spring.umc.domain.store.entity.QStore;
import spring.umc.domain.review.repository.ReviewQueryDsl;

@Service
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService { // 리뷰 조회 관련

    private final ReviewQueryDsl reviewQueryDsl;
    private final MemberRepository memberRepository;

    public ReviewQueryServiceImpl(
            @Qualifier("reviewQueryDsl") ReviewQueryDsl reviewQueryDsl,
            MemberRepository memberRepository
    ) {
        this.reviewQueryDsl = reviewQueryDsl;
        this.memberRepository = memberRepository;
    }

    @Override
    public Page<ReviewResDTO> getMyReviews(Long userId, String storeName, Integer ratingGroup, Pageable pageable) {

        User user = memberRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다. userId=" + userId));

        QReview review = QReview.review;
        QStore store = QStore.store;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(review.user.id.eq(userId));

        if (storeName != null && !storeName.isBlank()) {
            builder.and(store.name.containsIgnoreCase(storeName));
        }

        if (ratingGroup != null) {
            builder.and(review.rating.between(ratingGroup, ratingGroup + 1.0));
        }

        return reviewQueryDsl.findMyReviewsByBuilder(builder, pageable);
    }
}
