package spring.umc.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.umc.domain.review.dto.ReviewReqDTO;
import spring.umc.domain.review.dto.ReviewResDTO;
import spring.umc.domain.review.service.command.ReviewCommandService;
import spring.umc.domain.review.service.query.ReviewQueryService;
import spring.umc.global.apiPayload.ApiResponse;
import spring.umc.global.apiPayload.code.GeneralSuccessCode;


@RestController
@RequestMapping("/api/my/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewQueryService reviewService;
    private final ReviewCommandService reviewCommandService;

    /**
     * 내가 작성한 리뷰 목록 조회 API
     *  - /api/my/reviews : 내가 작성한 리뷰 목록 조회
     *  - 필터링 : 가게명(storeName), 별점대(ratingGroup)
     *  - 페이징 : Pageable (page, size)
     */
    @GetMapping
    public ResponseEntity<ApiResponse<Page<ReviewResDTO>>> getMyReviews(
            @RequestParam(name = "userId") Long userId,
            @RequestParam(name = "storeName", required = false) String storeName,
            @RequestParam(name = "ratingGroup", required = false) Integer ratingGroup,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        Page<ReviewResDTO> reviews = reviewService.getMyReviews(userId, storeName, ratingGroup, pageable);
        return ResponseEntity.ok(ApiResponse.onSuccess(GeneralSuccessCode.REVIEW_LIST_OK, reviews));
    }


    /**
     * 리뷰 생성 API
     * - /api/my/reviews
     */

    @PostMapping
    public ResponseEntity<ReviewResDTO.CreateDTO> createReview(
            @RequestBody ReviewReqDTO.CreateDTO dto
    ) {
        return ResponseEntity.ok(reviewCommandService.createReview(dto));
    }

}
