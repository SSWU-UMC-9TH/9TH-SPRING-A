package spring.umc.domain.review.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.umc.domain.review.dto.ReviewReqDTO;
import spring.umc.domain.review.dto.ReviewResDTO;
import spring.umc.domain.review.entity.Review;
import spring.umc.domain.review.exeption.code.ReviewSuccessCode;
import spring.umc.domain.review.service.ReviewQueryService;
import spring.umc.domain.review.service.command.ReviewCommandService;
import spring.umc.global.apiPayload.ApiResponse;
import spring.umc.global.apiPayload.code.GeneralSuccessCode;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewQueryService reviewQueryService;
    private final ReviewCommandService reviewCommandService;

    // 가게 리뷰 등록
    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.CreateDTO> createReview(
            @PathVariable Long storeId,
            @RequestBody @Valid ReviewReqDTO.CreateDTO dto
    ) {
        return ApiResponse.onSuccess(
                ReviewSuccessCode.CREATED,
                reviewCommandService.createReview(storeId, dto)
        );
    }

//    @GetMapping("/reviews/search")
//    public List<Review> searchReview(
//            @RequestParam String query,
//            @RequestParam String type
//    ) {
//        List<Review> result = reviewQueryService.searchReview(query, type);
//        return result;
//    }
//    @GetMapping("/myreviews/search")
//    public List<Review> mysearchReview(
//            @RequestParam(name = "storename", required = false) String storeName,
//            @RequestParam(name = "star", required = false) Integer star
//    ) {
//        List<Review> result = reviewQueryService.mysearchReview(storeName, star);
//        return result;
//    }
@GetMapping("/reviews/search")
public ApiResponse<List<ReviewResDTO.Summary>> searchReview(
        @RequestParam String query,
        @RequestParam String type
) {
    List<ReviewResDTO.Summary> result = reviewQueryService.searchReview(query, type);
    return ApiResponse.onSuccess(GeneralSuccessCode._OK, result);
}

    @GetMapping("/myreviews/search")
    public ApiResponse<List<ReviewResDTO.Summary>> mysearchReview(
            @RequestParam(name = "storename", required = false) String storeName,
            @RequestParam(name = "star", required = false) Integer star
    ) {
        List<ReviewResDTO.Summary> result = reviewQueryService.mysearchReview(storeName, star);
        return ApiResponse.onSuccess(GeneralSuccessCode._OK, result);
    }
}