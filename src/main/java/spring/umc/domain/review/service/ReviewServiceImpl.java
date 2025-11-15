package spring.umc.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.umc.domain.member.entity.Member;
import spring.umc.domain.member.exception.MemberException;
import spring.umc.domain.member.exception.code.MemberErrorCode;
import spring.umc.domain.member.repository.MemberRepository;
import spring.umc.domain.review.entity.Review;
import spring.umc.domain.review.entity.ReviewImage;
import spring.umc.domain.review.repository.ReviewImageRepository;
import spring.umc.domain.review.repository.ReviewRepository;
import spring.umc.domain.store.entity.Store;
import spring.umc.domain.store.exception.StoreException;
import spring.umc.domain.store.exception.code.StoreErrorCode;
import spring.umc.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final ReviewImageRepository reviewImageRepository;

    @Override
    @Transactional
    public Review createReview(Long memberId, Long storeId, String content, Double score, String imageUrl) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        ReviewImage reviewImage = ReviewImage.builder()
                .reviewImageUrl(imageUrl)
                .sequence(1)
                .build();

        ReviewImage savedReviewImage = reviewImageRepository.save(reviewImage);

        Review review = Review.builder()
                .member(member)
                .store(store)
                .content(content)
                .score(score)
                .reviewImage(savedReviewImage)
                .build();

        Review savedReview = reviewRepository.save(review);

        Double newAverageScore = reviewRepository.getAverageScoreByStoreId(store.getStoreId())
                .orElse(0.0);

        store.updateScore(newAverageScore.floatValue());

        return savedReview;
    }

    @Override
    public Page<Review> searchReviews(Long memberId, Long storeId, Double minScore, Pageable pageable) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return reviewRepository.searchReviews(memberId, storeId, minScore, pageable);
    }
}