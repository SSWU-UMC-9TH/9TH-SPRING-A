package spring.umc.domain.review.service;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<Review> searchMyReview(Long memberId, Integer pageable) {
        Pageable p = PageRequest.of(pageable, 5);
        return reviewRepository.findByMemberId(memberId, p);
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

    @Override
    public ReviewResponseDTO.ReviewPreViewListDTO findReview(
            String storeName, Integer page
    ){
        // - 가게를 가져온다 (가게 존재 여부 검증)
        Store store = storeRepository.findByName(storeName)
                //    - 없으면 예외 터뜨린다
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.STORE_NOT_FOUND));

        //- 가게에 맞는 리뷰를 가져온다 (Offset 페이징)
        PageRequest pageRequest = PageRequest.of(page, 5);
        Page<Review> result = reviewRepository.findAllByStore(store, pageRequest);

        //- 결과를 응답 DTO로 변환한다 (컨버터 이용)
        return ReviewConverter.toReviewPreviewListDTO(result);
    }

}