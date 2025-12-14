package spring.umc.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.umc.domain.member.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    List<Member> findByNameAndDeletedAtIsNull(String name);
    //-> SELECT * FROM `user` WHERE name = '마크' AND deleted_at is null;

    // 이렇게도 가능(@Query 어노테이션)
//    @Query("select m from Member m where m.name = :name and m.deleted_at is null")
//    List<Member> findActiveMember(@Param("name") String name);

    Optional<Member> findById(Long id);
}
