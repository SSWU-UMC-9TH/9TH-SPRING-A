package spring.umc.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.umc.domain.member.entity.FoodCategory;
import spring.umc.domain.member.entity.Member;
import spring.umc.domain.member.entity.PreferFood;
import spring.umc.domain.member.enums.Gender;
import spring.umc.domain.member.enums.Status;
import spring.umc.domain.member.exception.FoodCategoryException;
import spring.umc.domain.member.exception.MemberException;
import spring.umc.domain.member.exception.code.FoodCategoryErrorCode;
import spring.umc.domain.member.exception.code.MemberErrorCode;
import spring.umc.domain.member.repository.FoodCategoryRepository;
import spring.umc.domain.member.repository.MemberRepository;
import spring.umc.domain.member.repository.PreferFoodRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PreferFoodRepository preferFoodRepository;
    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    public Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
    }

    @Override
    public Page<Member> searchMembers(String nameKeyword, Gender gender, Status status, Pageable pageable) {
        return memberRepository.searchMembers(nameKeyword, gender, status, pageable);
    }

    /**
     * 선호 음식 변경
     */
    @Override
    @Transactional
    public void updatePreferFoods(Long memberId, List<Long> categoryIds) {
        Member member = findMemberById(memberId);

        List<PreferFood> existingPrefers = preferFoodRepository.findByMember(member);

        preferFoodRepository.deleteAll(existingPrefers);

        List<FoodCategory> newCategories = foodCategoryRepository.findAllById(categoryIds);

        if (newCategories.size() != categoryIds.size()) {
            throw new FoodCategoryException(FoodCategoryErrorCode.FOOD_CATEGORY_NOT_FOUND);
        }

        List<PreferFood> newPrefers = newCategories.stream()
                .map(category -> PreferFood.builder()
                        .member(member)
                        .category(category)
                        .build())
                .collect(Collectors.toList());

        preferFoodRepository.saveAll(newPrefers);
    }
}