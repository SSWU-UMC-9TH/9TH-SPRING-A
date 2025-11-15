package spring.umc.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import spring.umc.domain.review.converter.ReviewConverter;
import spring.umc.domain.review.dto.req.ReviewRequestDTO;
import spring.umc.domain.review.dto.res.ReviewResponseDTO;
import spring.umc.domain.review.dto.res.ReviewResponseDTO.CreateReviewResultDTO;
import spring.umc.domain.review.entity.Review;
import spring.umc.domain.review.service.ReviewService;
import spring.umc.global.apiPayload.ApiResponse;
import spring.umc.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * 리뷰 생성
     */
    @PostMapping
    public ApiResponse<CreateReviewResultDTO> createReview(
            @RequestParam Long memberId,
            @RequestParam Long storeId,
            @RequestBody ReviewRequestDTO.CreateReviewDTO request) {

        Review review = reviewService.createReview(
                memberId,
                storeId,
                request.getContent(),
                request.getScore(),
                request.getImageUrl()
        );
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, ReviewConverter.toCreateReviewResultDTO(review));
    }

    /**
     * 리뷰 동적 검색 (QueryDSL)
     */
    @GetMapping("/search")
    public ApiResponse<ReviewResponseDTO.ReviewPageListDTO> searchReviews(
            @RequestParam(required = false) Long memberId,
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) Double minScore,
            @PageableDefault(size = 10) Pageable pageable) {

        Page<Review> reviewPage = reviewService.searchReviews(memberId, storeId, minScore, pageable);
        GeneralSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, ReviewConverter.toReviewPageListDTO(reviewPage));
    }
}