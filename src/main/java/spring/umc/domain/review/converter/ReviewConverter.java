package spring.umc.domain.review.converter;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component; // <-- 1. 스프링 부품으로 등록!
import spring.umc.domain.member.entity.Member;
import spring.umc.domain.review.dto.ReviewReqDTO;
import spring.umc.domain.review.dto.ReviewResponseDTO;
import spring.umc.domain.review.entity.Review;
import spring.umc.domain.store.entity.Store;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReviewConverter {

    // 1. DTO -> Entity (리뷰 생성할 때)
    public static Review toEntity(ReviewReqDTO.AddReviewDTO request, Member member, Store store) {
        return Review.builder()
                .star(request.getStar())
                .content(request.getContent())
                .member(member) // 리뷰 쓴 사람 (하드코딩된 유저)
                .store(store)   // 리뷰 달린 가게
                .build();
    }



    // entity->dto 변환
    public ReviewResponseDTO.ReviewDto toReviewDto(Review review) {

        return ReviewResponseDTO.ReviewDto.builder()
                .reviewId(review.getId())
                .memberName(review.getMember().getName()) //
                .star(review.getStar())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }


    public List<ReviewResponseDTO.ReviewDto> toReviewDtoList(List<Review> reviewList) {

        return reviewList.stream()
                .map(this::toReviewDto)
                .collect(Collectors.toList());
    }

    public static ReviewResponseDTO.CreateReviewResultDto toCreateReviewResultDto(Review review) {
        return ReviewResponseDTO.CreateReviewResultDto.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }
    // result -> DTO
    public static ReviewResponseDTO.ReviewPreViewListDTO toReviewPreviewListDTO(
            Page<Review> result
    ){
        return ReviewResponseDTO.ReviewPreViewListDTO.builder()
                .reviewList(result.getContent().stream()
                        .map(ReviewConverter::toReviewPreviewDTO)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static ReviewResponseDTO.ReviewPreViewDTO toReviewPreviewDTO(
            Review review
    ){
        return ReviewResponseDTO.ReviewPreViewDTO.builder()
                .ownerName(review.getMember().getName())
                .score(review.getStar())
                .body(review.getContent())
                .createdAt(LocalDate.from(review.getCreatedAt()))
                .build();
    }
}