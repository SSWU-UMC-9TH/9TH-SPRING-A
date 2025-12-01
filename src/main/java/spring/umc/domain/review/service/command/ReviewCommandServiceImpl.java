package spring.umc.domain.review.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.umc.domain.member.entity.Member;
import spring.umc.domain.member.exeption.MemberException;
import spring.umc.domain.member.exeption.code.MemberErrorCode;
import spring.umc.domain.member.repository.MemberRepository;
import spring.umc.domain.review.converter.ReviewConverter;
import spring.umc.domain.review.dto.ReviewReqDTO;
import spring.umc.domain.review.dto.ReviewResDTO;
import spring.umc.domain.review.entity.Review;
import spring.umc.domain.review.repository.ReviewRepository;
import spring.umc.domain.store.entity.Store;
import spring.umc.domain.store.exeption.StoreException;
import spring.umc.domain.store.exeption.code.StoreErrorCode;
import spring.umc.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl  implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public ReviewResDTO.CreateDTO createReview(
            Long storeId,
            ReviewReqDTO.CreateDTO dto
    ) {
        // 1. 회원 존재 여부 검증
        Member member = memberRepository.findById(dto.memberId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        // 2. 가게 존재 여부 검증
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        // 3. 리뷰 엔티티 생성
        Review review = ReviewConverter.toReview(dto, member, store);

        // 4. DB 저장
        reviewRepository.save(review);

        // 5. 응답 DTO 반환
        return ReviewConverter.toCreateDTO(review);
    }
}
