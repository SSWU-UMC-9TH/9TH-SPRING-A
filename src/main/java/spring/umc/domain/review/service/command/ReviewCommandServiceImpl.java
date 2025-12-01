package spring.umc.domain.review.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.umc.domain.member.entity.User;
import spring.umc.domain.member.repository.MemberRepository;
import spring.umc.domain.review.converter.ReviewConverter;
import spring.umc.domain.review.dto.ReviewReqDTO;
import spring.umc.domain.review.dto.ReviewResDTO;
import spring.umc.domain.review.entity.Review;
import spring.umc.domain.review.exception.ReviewException;
import spring.umc.domain.review.exception.code.ReviewErrorCode;
import spring.umc.domain.review.repository.ReviewRepository;
import spring.umc.domain.store.entity.Store;
import spring.umc.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Override
    public ReviewResDTO.CreateDTO createReview(ReviewReqDTO.CreateDTO dto) {

        // 가게 검증
        Store store = storeRepository.findById(dto.storeId())
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.STORE_NOT_FOUND));

        // 사용자 검증
        User user = memberRepository.findById(dto.userId())
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.USER_NOT_FOUND));

        // Review 엔티티로 변환
        Review review = ReviewConverter.toReview(dto, user, store);

        // 저장
        reviewRepository.save(review);

        // 응답 DTO 변환
        return ReviewConverter.toCreateDTO(review);
    }
}
