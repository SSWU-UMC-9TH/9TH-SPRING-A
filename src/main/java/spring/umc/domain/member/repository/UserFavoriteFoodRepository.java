package spring.umc.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.umc.domain.member.entity.UserFavoriteFood;

public interface UserFavoriteFoodRepository extends JpaRepository<UserFavoriteFood, Long> {
}

