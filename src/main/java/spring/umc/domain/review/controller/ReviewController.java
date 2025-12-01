package spring.umc.domain.review.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import spring.umc.domain.review.dto.ReviewReqDTO;
import spring.umc.domain.review.dto.ReviewResDTO;
import spring.umc.domain.review.entity.Review;
import spring.umc.domain.review.exeption.code.ReviewSuccessCode;
import spring.umc.domain.review.service.ReviewQueryService;
import spring.umc.domain.review.service.command.ReviewCommandService;
import spring.umc.global.annotation.ValidPage;
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

    @Operation(
            summary = "내가 작성한 리뷰 목록",
            description = "회원이 작성한 리뷰를 페이지 단위로 조회합니다. 한 페이지에 10개씩 반환됩니다."
    )
    @GetMapping("/{memberId}/reviews")
    public ApiResponse<Page<ReviewResDTO.Summary>> getMyReviews(
            @Parameter(description = "회원 ID") @PathVariable Long memberId,
            @Parameter(description = "페이지 번호 (1부터 시작)", example = "1")
            @ValidPage @RequestParam(name = "page", defaultValue = "1") Integer page
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode._OK,
                /* 성공코드 있으면 넣고 없으면 null 도 가능 */
                reviewQueryService.getMyReviews(memberId, page)
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