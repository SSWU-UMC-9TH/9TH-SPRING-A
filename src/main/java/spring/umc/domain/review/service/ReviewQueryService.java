package spring.umc.domain.review.service;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.umc.domain.member.entity.QMember;
import spring.umc.domain.review.converter.ReviewConverter;
import spring.umc.domain.review.dto.ReviewResDTO;
import spring.umc.domain.review.entity.QReview;
import spring.umc.domain.review.entity.Review;
import spring.umc.domain.review.repository.ReviewRepository;
import spring.umc.domain.store.entity.QLocation;
import spring.umc.domain.store.entity.QStore;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    @Transactional(readOnly = true)
    public Page<ReviewResDTO.Summary> getMyReviews(Long memberId, int page) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.DESC, "createdAt"));

        Page<Review> reviewPage = reviewRepository.findByMember_IdOrderByCreatedAtDesc(memberId, pageable);

        List<ReviewResDTO.Summary> content = ReviewConverter.toSummaryList(reviewPage.getContent());

        return new PageImpl<>(content, pageable, reviewPage.getTotalElements());
    }

    public List<ReviewResDTO.Summary> searchReview(String query, String type) {

        // Q클래스 정의
        QReview review = QReview.review;
        QLocation location = QLocation.location;

        // BooleanBuilder 정의
        BooleanBuilder builder = new BooleanBuilder();

        // BooleanBuilder 사용

        // 동적 쿼리: 검색 조건
        if (type.equals("location")) {
            builder.and(location.name.contains(query));
        }
        if (type.equals("star")) {
            builder.and(review.rating.goe(Float.parseFloat(query)));
        }
        if (type.equals("both")) {

            // & 기준 변환
            String firstQuery = query.split("&")[0];
            String secondQuery = query.split("&")[1];

            // 동적 쿼리
            builder.and(location.name.contains(firstQuery));
            builder.and(review.rating.goe(Float.parseFloat(secondQuery)));
        }

        // Repository 사용 & 결과 매핑
        List<Review> reviewList = reviewRepository.searchReview(builder);

        // 리턴
        return ReviewConverter.toSummaryList(reviewList);
    }
    public List<ReviewResDTO.Summary> mysearchReview(String storeName, Integer star) {

        // Q클래스 정의
        QReview review = QReview.review;
        QLocation location = QLocation.location;
        QStore store = QStore.store;
        QMember member = QMember.member;

        // BooleanBuilder 정의
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(member.id.eq(9001L));

        // BooleanBuilder 사용
        if (storeName != null && !storeName.isBlank()) {
            builder.and(store.name.contains(storeName));
        }

        // 3) 별점대 필터 (5/4/3/2/1)
        if (star != null) {
            applyStar(builder, review, star);
        }


        // Repository 사용 & 결과 매핑
        List<Review> reviewList = reviewRepository.mysearchReview(builder);

        // 리턴
        return ReviewConverter.toSummaryList(reviewList);
    }
    private void applyStar(BooleanBuilder where, QReview r, Integer bucket) {
        switch (bucket) {
            case 5 -> where.and(r.rating.goe(5.0f)); // 딱 5.0만 보려면 eq(5.0f)로 변경
            case 4 -> where.and(r.rating.goe(4.0f).and(r.rating.lt(5.0f)));
            case 3 -> where.and(r.rating.goe(3.0f).and(r.rating.lt(4.0f)));
            case 2 -> where.and(r.rating.goe(2.0f).and(r.rating.lt(3.0f)));
            case 1 -> where.and(r.rating.goe(1.0f).and(r.rating.lt(2.0f)));
            case 0 -> where.and(r.rating.lt(1.0f));
            default -> { /* 적용 안 함 */ }
        }
    }


//    @Override
//    public List<Review> searchReview(
//            Predicate predicate
//    ) {
//        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
//        QReview review = QReview.review;
//
//        return queryFactory
//                .selectFrom(review)
//                .where(predicate)
//                .fetch();
//    }
//
//    public List<Review> queryTest(String name) {
//        // Q클래스 정의
//        QReview review = QReview.review;
//
//        // BooleanBuilder 정의
//        BooleanBuilder builder = new BooleanBuilder();
//
//        // BooleanBuilder 사용 (예: 가게명 포함 검색)
//        builder.and(review.store.name.contains(name));
//
//        // Repository 사용 & 결과 매핑
//        List<Review> reviewList = reviewRepository.searchReview(builder);
//        return reviewList;
//    }
}
