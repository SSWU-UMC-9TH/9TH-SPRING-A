package spring.umc.domain.store.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.dsl.StringTemplate;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStore is a Querydsl query type for Store
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStore extends EntityPathBase<Store> {

    private static final long serialVersionUID = -1312790327L;

    public static final QStore store = new QStore("store");

    public final spring.umc.global.QBaseEntity _super = new spring.umc.global.QBaseEntity(this);

    public final EnumPath<spring.umc.domain.member.enums.Address> address = createEnum("address", spring.umc.domain.member.enums.Address.class);

    public final TimePath<java.time.LocalTime> closingHours = createTime("closingHours", java.time.LocalTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath detailAddress = createString("detailAddress");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath lane = createString("lane");

    public final ListPath<spring.umc.domain.mission.entity.Mission, spring.umc.domain.mission.entity.QMission> missionList = this.<spring.umc.domain.mission.entity.Mission, spring.umc.domain.mission.entity.QMission>createList("missionList", spring.umc.domain.mission.entity.Mission.class, spring.umc.domain.mission.entity.QMission.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final TimePath<java.time.LocalTime> openingHours = createTime("openingHours", java.time.LocalTime.class);

    public final StringPath phoneNum = createString("phoneNum");

    public final ListPath<spring.umc.domain.review.entity.Review, spring.umc.domain.review.entity.QReview> reviewList = this.<spring.umc.domain.review.entity.Review, spring.umc.domain.review.entity.QReview>createList("reviewList", spring.umc.domain.review.entity.Review.class, spring.umc.domain.review.entity.QReview.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QStore(String variable) {
        super(Store.class, forVariable(variable));
    }

    public QStore(Path<? extends Store> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStore(PathMetadata metadata) {
        super(Store.class, metadata);
    }

}

