package spring.umc.domain.review.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.dsl.StringTemplate;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReview is a Querydsl query type for Review
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReview extends EntityPathBase<Review> {

    private static final long serialVersionUID = -246578125L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReview review = new QReview("review");

    public final spring.umc.global.entity.QBaseEntity _super = new spring.umc.global.entity.QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final spring.umc.domain.member.entity.QMember member;

    public final ListPath<spring.umc.domain.review.entity.mapping.ReviewPhoto, spring.umc.domain.review.entity.mapping.QReviewPhoto> photos = this.<spring.umc.domain.review.entity.mapping.ReviewPhoto, spring.umc.domain.review.entity.mapping.QReviewPhoto>createList("photos", spring.umc.domain.review.entity.mapping.ReviewPhoto.class, spring.umc.domain.review.entity.mapping.QReviewPhoto.class, PathInits.DIRECT2);

    public final ListPath<Reply, QReply> replies = this.<Reply, QReply>createList("replies", Reply.class, QReply.class, PathInits.DIRECT2);

    public final NumberPath<Float> star = createNumber("star", Float.class);

    public final spring.umc.domain.store.entity.QStore store;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QReview(String variable) {
        this(Review.class, forVariable(variable), INITS);
    }

    public QReview(Path<? extends Review> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReview(PathMetadata metadata, PathInits inits) {
        this(Review.class, metadata, inits);
    }

    public QReview(Class<? extends Review> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new spring.umc.domain.member.entity.QMember(forProperty("member")) : null;
        this.store = inits.isInitialized("store") ? new spring.umc.domain.store.entity.QStore(forProperty("store"), inits.get("store")) : null;
    }

}

