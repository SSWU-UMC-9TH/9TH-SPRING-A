package spring.umc.domain.review.converter;

import spring.umc.domain.member.entity.Member;
import spring.umc.domain.review.dto.ReviewReqDTO;
import spring.umc.domain.review.dto.ReviewResDTO;
import spring.umc.domain.review.entity.Review;
import spring.umc.domain.store.entity.Store;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {
    // DTO -> Entity
    public static Review toReview(
            ReviewReqDTO.CreateDTO dto,
            Member member,
            Store store
    ) {
        return Review.builder()
                .member(member)
                .store(store)
                .rating(dto.rating())
                .content(dto.content())
                .build();
    }

    // Entity -> DTO
    public static ReviewResDTO.CreateDTO toCreateDTO(
            Review review
    ) {
        return ReviewResDTO.CreateDTO.builder()
                .reviewId(review.getId())
                .memberId(review.getMember().getId())
                .storeId(review.getStore().getId())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResDTO.Summary toSummary(Review r) {
        return ReviewResDTO.Summary.builder()
                .id(r.getId())
                .storeName(r.getStore() != null ? r.getStore().getName() : null)
                .locationName(
                        (r.getStore() != null && r.getStore().getLocation() != null)
                                ? r.getStore().getLocation().getName() : null
                )
                .star(r.getRating())
                .content(r.getContent())
                .memberNickname(r.getMember() != null ? r.getMember().getName() : null)
                .createdAt(r.getCreatedAt())
                .build();
    }

    public static List<ReviewResDTO.Summary> toSummaryList(List<Review> list) {
        return list.stream().map(ReviewConverter::toSummary).collect(Collectors.toList());
    }
}
