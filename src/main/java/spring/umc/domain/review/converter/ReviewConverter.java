package spring.umc.domain.review.converter;

import spring.umc.domain.member.entity.User;
import spring.umc.domain.review.dto.ReviewReqDTO;
import spring.umc.domain.review.dto.ReviewResDTO;
import spring.umc.domain.review.entity.Review;
import spring.umc.domain.store.entity.Store;

public class ReviewConverter {

    public static Review toReview(ReviewReqDTO.CreateDTO dto, User user, Store store) {
        return Review.builder()
                .user(user)
                .store(store)
                .content(dto.content())
                .rating(dto.rating())
                .build();
    }

    public static ReviewResDTO.CreateDTO toCreateDTO(Review review) {
        return ReviewResDTO.CreateDTO.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .userId(review.getUser().getId())
                .build();
    }
}
