package spring.umc.domain.review.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import spring.umc.domain.review.dto.ReviewReqDTO;
import spring.umc.domain.review.entity.Review;
import spring.umc.domain.review.exception.code.ReviewSuccessCode;
import spring.umc.domain.review.service.ReviewQueryService;
import spring.umc.domain.review.converter.ReviewConverter;
import spring.umc.domain.review.dto.ReviewResponseDTO;

import spring.umc.global.annotation.CheckPage;
import spring.umc.global.apiPayload.ApiResponse; // <- 1. ApiResponse 임포트
import spring.umc.global.apiPayload.code.GeneralSuccessCode; // <- 2. SuccessCode 임포트

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewQueryService reviewQueryService;
    private final ReviewConverter reviewConverter;

    @GetMapping("/reviews/search")

    public ApiResponse<List<ReviewResponseDTO.ReviewDto>> searchReview(
            @RequestParam String type,
            @RequestParam String query
    ){
        List<Review> reviewList = reviewQueryService.searchReview(type, query);
        List<ReviewResponseDTO.ReviewDto> dtoList = reviewConverter.toReviewDtoList(reviewList);


        return ApiResponse.onSuccess(GeneralSuccessCode.OK, dtoList);
    }

    @GetMapping("/reviews/my")
    @Operation(summary = "내가 작성한 리뷰 확인 API", description = "(유저 ID는 1로 고정)")
    @Parameter(name = "page", description = "페이지 번호 (1부터 시작), 쿼리 스트링으로 전달")
    // Paging 처리를 위한 커스텀 어노테이션 적용!
    public ApiResponse<Page<ReviewResponseDTO.ReviewDto>> searchMyReview(
            @CheckPage Integer pageable // ✨ 우리가 만든 커스텀 어노테이션 사용!
    ) {
        // 1. 유저 ID를 1L로 하드코딩 (✅ 다경의 현명한 전략!)
        final Long memberId = 1L;

        // 2. Service 계층에서 Paging 적용하여 조회
        Page<Review> reviewPage = reviewQueryService.searchMyReview(memberId, pageable);

        // 3. Page<Entity>를 Page<DTO>로 변환
        Page<ReviewResponseDTO.ReviewDto> dtoList = reviewPage.map(reviewConverter::toReviewDto);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, dtoList);
    }

    @PostMapping("/stores/{storeId}/reviews")
    @Operation(summary = "가게 리뷰 작성 API", description = "특정 가게에 리뷰를 작성합니다. (유저 ID는 1로 고정)")
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
    })
    public ApiResponse<ReviewResponseDTO.CreateReviewResultDto> addReview(
            @PathVariable(name = "storeId") Long storeId,
            @RequestBody @Valid ReviewReqDTO.AddReviewDTO request
    ) {
        // 하드코딩 유저 ID 1L
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, reviewQueryService.addReview(1L, storeId, request));
    }

    // 가게의 리뷰 목록 조회 전체
    @Operation(
            summary = "가게의 리뷰 목록 조회 API By 마크 (개발 중)",
            description = "특정 가게의 리뷰를 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping("/reviews")
    public ApiResponse<ReviewResponseDTO.ReviewPreViewListDTO> findReview(
            @RequestParam String storeName,
            @RequestParam(defaultValue="0") Integer page
    ){

        ReviewSuccessCode code = ReviewSuccessCode.REVIEW_FOUND_SUCCESS;
        return ApiResponse.onSuccess(code, reviewQueryService.findReview(storeName,page));
    }
}