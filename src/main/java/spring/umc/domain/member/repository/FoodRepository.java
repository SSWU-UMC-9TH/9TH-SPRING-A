package spring.umc.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.umc.domain.member.entity.Food;
import spring.umc.domain.member.enums.FoodCategory;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {

    // 이름(카테고리)으로 음식 찾기
    // 예: foodRepository.findByName(FoodCategory.KOREAN_FOOD);
    Optional<Food> findByName(FoodCategory name);

    // 혹시 중복된 음식인지 검사할 때
    boolean existsByName(FoodCategory name);
}