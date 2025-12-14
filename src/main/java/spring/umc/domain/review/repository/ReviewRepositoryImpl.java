package spring.umc.domain.review.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring.umc.domain.member.entity.QMember;
import spring.umc.domain.review.entity.QReview;
import spring.umc.domain.review.entity.Review;
import spring.umc.domain.store.entity.QLocation;
import spring.umc.domain.store.entity.QStore;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewQueryDsl {

    private final EntityManager em;              // ✅ ReviewRepository 주입 금지

    @Override
    public List<Review> searchReview(Predicate predicate) {
        JPAQueryFactory qf = new JPAQueryFactory(em);
        QReview r = QReview.review;
        QStore store = QStore.store;
        QLocation location = QLocation.location;

        return qf.selectFrom(r)
                .leftJoin(store).on(store.id.eq(r.store.id))
                .leftJoin(location).on(location.id.eq(store.location.id))
                .where(predicate)
                .fetch();
    }
    @Override
    public List<Review> mysearchReview(Predicate predicate) {
        JPAQueryFactory qf = new JPAQueryFactory(em);
        QReview r = QReview.review;
        QStore store = QStore.store;
        QLocation location = QLocation.location;
        QMember m = QMember.member;

        return qf.selectFrom(r)
                .leftJoin(store).on(store.id.eq(r.store.id))
                .leftJoin(location).on(location.id.eq(store.location.id))
                .leftJoin(r.member, m).fetchJoin()
                .where(predicate)
                .fetch();
    }
}
