package spring.umc.domain.review.converter;

import org.springframework.data.domain.Page;
import spring.umc.domain.review.dto.res.ReviewResponseDTO;
import spring.umc.domain.review.entity.Review;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    /**
     * 리뷰 생성 결과 DTO
     */
    public static ReviewResponseDTO.CreateReviewResultDTO toCreateReviewResultDTO(Review review) {
        return ReviewResponseDTO.CreateReviewResultDTO.builder()
                .reviewId(review.getReviewId())
                .memberName(review.getMember().getName())
                .storeName(review.getStore().getName())
                .createdAt(review.getCreatedAt())
                .build();
    }

    /**
     * Entity -> DTO (단일 조회용)
     */
    public static ReviewResponseDTO.ReviewDTO toReviewDTO(Review review) {
        return ReviewResponseDTO.ReviewDTO.builder()
                .reviewId(review.getReviewId())
                .memberName(review.getMember().getName())
                .storeName(review.getStore().getName())
                .score(review.getScore())
                .content(review.getContent())
                .reviewImageUrl(review.getReviewImage().getReviewImageUrl())
                .createdAt(review.getCreatedAt())
                .build();
    }

    /**
     * Page<Entity> -> DTO (목록)
     */
    public static ReviewResponseDTO.ReviewPageListDTO toReviewPageListDTO(Page<Review> reviewPage) {
        List<ReviewResponseDTO.ReviewDTO> reviewList = reviewPage.getContent().stream()
                .map(ReviewConverter::toReviewDTO)
                .collect(Collectors.toList());

        return ReviewResponseDTO.ReviewPageListDTO.builder()
                .reviewList(reviewList)
                .listSize(reviewList.size())
                .totalPage(reviewPage.getTotalPages())
                .totalElements(reviewPage.getTotalElements())
                .isFirst(reviewPage.isFirst())
                .isLast(reviewPage.isLast())
                .build();
    }
}