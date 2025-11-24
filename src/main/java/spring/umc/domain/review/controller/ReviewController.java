package spring.umc.domain.review.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.umc.domain.review.dto.ReviewReqDTO;
import spring.umc.domain.review.entity.Review;
import spring.umc.domain.review.service.ReviewQueryService;
import spring.umc.domain.review.converter.ReviewConverter;
import spring.umc.domain.review.dto.ReviewResponseDTO;

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

    public ApiResponse<List<ReviewResponseDTO.ReviewDto>> searchMyReview(
            @RequestParam Long memberId,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String query
    ){
        List<Review> reviewList = reviewQueryService.searchMyReview(memberId, type,query);
        List<ReviewResponseDTO.ReviewDto> dtoList = reviewConverter.toReviewDtoList(reviewList);


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
}