package spring.umc.domain.member.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.umc.domain.member.converter.MemberConverter;
import spring.umc.domain.member.dto.MemberReqDTO;
import spring.umc.domain.member.dto.MemberResDTO;
import spring.umc.domain.member.exception.MemberException;
import spring.umc.domain.member.exception.code.FoodCategoryErrorCode;
import spring.umc.domain.member.exception.code.MemberErrorCode;
import spring.umc.domain.member.repository.MemberRepository;
import spring.umc.domain.member.repository.UserFavoriteFoodRepository;
import spring.umc.domain.member.entity.FoodCategory;
import spring.umc.domain.member.entity.User;
import spring.umc.domain.member.entity.UserFavoriteFood;
import spring.umc.domain.member.repository.FoodCategoryRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final UserFavoriteFoodRepository userFavoriteFoodRepository;

    @Override
    @Transactional
    public MemberResDTO.JoinDTO signup(MemberReqDTO.JoinDTO dto) {

        // User 생성
        User user = MemberConverter.toUser(dto);
        memberRepository.save(user);

        // 선호 음식 카테고리 존재할 경우
        if (dto.preferCategory() != null && !dto.preferCategory().isEmpty()) {
            List<UserFavoriteFood> favoriteList = new ArrayList<>();

            for (Long categoryId : dto.preferCategory()) {

                // FoodCategory 조회
                FoodCategory category = foodCategoryRepository.findById(categoryId)
                        .orElseThrow(() -> new MemberException(FoodCategoryErrorCode.CATEGORY_NOT_FOUND));

                // UserFavoriteFood 생성
                UserFavoriteFood favorite = UserFavoriteFood.builder()
                        .user(user)
                        .foodCategory(category)
                        .build();

                favoriteList.add(favorite);
            }

            // 전체 저장
            userFavoriteFoodRepository.saveAll(favoriteList);
        }

        // 응답 DTO 생성
        return MemberConverter.toJoinDTO(user);
    }
}

