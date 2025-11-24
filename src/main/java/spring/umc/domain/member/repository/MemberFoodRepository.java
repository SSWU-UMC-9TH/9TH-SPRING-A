package spring.umc.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.umc.domain.member.entity.Userfood;
import java.util.List;

public interface MemberFoodRepository extends JpaRepository<Userfood, Long> {


    List<Userfood> findAllByMemberId(Long memberId);


    boolean existsByMemberIdAndFoodId(Long memberId, Long foodId);
}