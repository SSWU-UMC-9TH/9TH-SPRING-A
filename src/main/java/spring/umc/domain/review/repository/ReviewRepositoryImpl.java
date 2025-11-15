package spring.umc.domain.review.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import spring.umc.domain.review.entity.Review;

// Q-Class import
import static spring.umc.domain.review.entity.QReview.review;
import static spring.umc.domain.member.entity.QMember.member;
import static spring.umc.domain.store.entity.QStore.store;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Review> searchReviews(Long memberId, Long storeId, Double minScore, Pageable pageable) {

        BooleanBuilder builder = new BooleanBuilder();

        if (memberId != null) {
            builder.and(review.member.memberId.eq(memberId));
        }

        if (storeId != null) {
            builder.and(review.store.storeId.eq(storeId));
        }

        if (minScore != null) {
            builder.and(review.score.goe(minScore));
        }

        List<Review> content = queryFactory
                .selectFrom(review)
                .leftJoin(review.member, member).fetchJoin()
                .leftJoin(review.store, store).fetchJoin()
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(review.createdAt.desc())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(review.count())
                .from(review)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }
}