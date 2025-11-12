package spring.umc.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.umc.domain.review.dto.ReviewResponseDto;
import spring.umc.domain.review.service.ReviewService;
import spring.umc.global.apiPayload.ApiResponse;
import spring.umc.global.apiPayload.code.GeneralSuccessCode;


@RestController
@RequestMapping("/api/my/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * 내가 작성한 리뷰 목록 조회 API
     *  - /api/my/reviews : 내가 작성한 리뷰 목록 조회
     *  - 필터링 : 가게명(storeName), 별점대(ratingGroup)
     *  - 페이징 : Pageable (page, size)
     */
    @GetMapping
    public ResponseEntity<ApiResponse<Page<ReviewResponseDto>>> getMyReviews(
            @RequestParam(name = "userId") Long userId,
            @RequestParam(name = "storeName", required = false) String storeName,
            @RequestParam(name = "ratingGroup", required = false) Integer ratingGroup,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        Page<ReviewResponseDto> reviews = reviewService.getMyReviews(userId, storeName, ratingGroup, pageable);
        return ResponseEntity.ok(ApiResponse.onSuccess(GeneralSuccessCode.REVIEW_LIST_OK, reviews));
    }

}
