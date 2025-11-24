package spring.umc.domain.review.service;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.umc.domain.member.entity.Member;
import spring.umc.domain.member.repository.MemberRepository;
import spring.umc.domain.review.converter.ReviewConverter;
import spring.umc.domain.review.dto.ReviewReqDTO;
import spring.umc.domain.review.dto.ReviewResponseDTO;
import spring.umc.domain.review.entity.QReview;
import spring.umc.domain.review.entity.Review;
import spring.umc.domain.review.repository.ReviewRepository;
import spring.umc.domain.review.exception.code.ReviewErrorCode;
import spring.umc.domain.store.entity.Store;
import spring.umc.domain.store.repository.StoreRepository;
import spring.umc.global.apiPayload.code.GeneralErrorCode;
import spring.umc.global.apiPayload.exception.GeneralException;
import java.util.List;

@Service
@RequiredArgsConstructor

public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;

    @Override
    public List<Review> searchReview(
            String type, String query
    ) {

        if (!type.equals("location") && !type.equals("star") && !type.equals("both")) {
            throw new GeneralException(ReviewErrorCode.REVIEW_SEARCH_TYPE_INVALID);
        }


        if (type.equals("star")) {
            try {
                Float.parseFloat(query);
            } catch (NumberFormatException e) {
                throw new GeneralException(ReviewErrorCode.REVIEW_STAR_QUERY_NOT_NUMBER);
            }
        }


        if (type.equals("both")) {
            try {
                String secondQuery = query.split("&")[1];
                Float.parseFloat(secondQuery);
            } catch (Exception e) {
                throw new GeneralException(ReviewErrorCode.REVIEW_STAR_QUERY_NOT_NUMBER);
            }
        }
        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        if (type.equals("location")) {
            builder.and(review.store.address.stringValue().containsIgnoreCase(query));
        }
        if (type.equals("star")){
            builder.and(review.star.goe(Float.parseFloat(query)));
        }
        if (type.equals("both")) {
            String firstQuery = query.split("&")[0];
            String secondQuery = query.split("&")[1];
            builder.and(review.store.address.stringValue().containsIgnoreCase(firstQuery));
            builder.and(review.star.goe(Float.parseFloat(secondQuery)));
        }

        List<Review> reviewList = reviewRepository.searchReview(builder);
        return reviewList;
    }

    @Override
    public List<Review> searchMyReview(Long memberId, String type, String query) {

        if (!type.equals("location") && !type.equals("star") && !type.equals("both")) {
            throw new GeneralException(ReviewErrorCode.REVIEW_SEARCH_TYPE_INVALID);
        }


        if (type.equals("star")) {
            try {
                Float.parseFloat(query);
            } catch (NumberFormatException e) {
                throw new GeneralException(ReviewErrorCode.REVIEW_STAR_QUERY_NOT_NUMBER);
            }
        }


        if (type.equals("both")) {
            try {
                String secondQuery = query.split("&")[1];
                Float.parseFloat(secondQuery);
            } catch (Exception e) {
                throw new GeneralException(ReviewErrorCode.REVIEW_STAR_QUERY_NOT_NUMBER);
            }
        }
        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(review.member.id.eq(memberId));

        if (type != null) {
            String safeType = type.trim();
            String safeQuery = (query != null) ? query.trim() : "";

            if (safeType.equalsIgnoreCase("location")) {
                builder.and(review.store.name.contains(safeQuery));
            }
            if (safeType.equalsIgnoreCase("star")){
                if (!safeQuery.isEmpty()) {
                    int starValue = Integer.parseInt(safeQuery);
                    if (starValue == 5) {
                        builder.and(review.star.eq(5.0f));
                    } else {
                        float lowerBound = (float) starValue;
                        float upperBound = lowerBound + 1.0f;
                        builder.and(review.star.goe(lowerBound).and(review.star.lt(upperBound)));
                    }
                }
            }
            if (safeType.equalsIgnoreCase("both")) {
                if (!safeQuery.isEmpty() && safeQuery.contains("&")) {
                    String storeNamePart = safeQuery.split("&")[0].trim();
                    String starPart = safeQuery.split("&")[1].trim();
                    if (!storeNamePart.isEmpty()) {
                        builder.and(review.store.name.contains(storeNamePart));
                    }
                    if (!starPart.isEmpty()) {
                        int starValue = Integer.parseInt(starPart);
                        if (starValue == 5) {
                            builder.and(review.star.eq(5.0f));
                        } else {
                            float lowerBound = (float) starValue;
                            float upperBound = lowerBound + 1.0f;
                            builder.and(review.star.goe(lowerBound).and(review.star.lt(upperBound)));
                        }
                    }
                }
            }
        }

        List<Review> reviewList = reviewRepository.searchReview(builder);
        return reviewList;
    }

    private final ReviewRepository ReviewRepository;
    private final MemberRepository memberRepository; // 유저 찾기용
    private final StoreRepository storeRepository;   // 가게 찾기용

    @Override
    @Transactional
    public ReviewResponseDTO.CreateReviewResultDto addReview(Long userId, Long storeId, ReviewReqDTO.AddReviewDTO request) {

        // 유저가 존재하는지 확인
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.MEMBER_NOT_FOUND));

        // 리뷰를 달 가게가 존재하는지 확인
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.STORE_NOT_FOUND));

        // DTO -> Entity 변환
        Review review = ReviewConverter.toEntity(request, member, store);

        //DB 저장
        reviewRepository.save(review);

        // 응답 DTO로 변환해서 반환
        return ReviewConverter.toCreateReviewResultDto(review);
    }
}