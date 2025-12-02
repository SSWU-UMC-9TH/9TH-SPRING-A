package spring.umc.domain.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.dsl.StringTemplate;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -2088186057L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMember member = new QMember("member1");

    public final spring.umc.global.QBaseEntity _super = new spring.umc.global.QBaseEntity(this);

    public final EnumPath<spring.umc.domain.member.enums.Address> address = createEnum("address", spring.umc.domain.member.enums.Address.class);

    public final ListPath<Alarm, QAlarm> alarmList = this.<Alarm, QAlarm>createList("alarmList", Alarm.class, QAlarm.class, PathInits.DIRECT2);

    public final DatePath<java.time.LocalDate> birth = createDate("birth", java.time.LocalDate.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath detailAddress = createString("detailAddress");

    public final StringPath email = createString("email");

    public final QFavor favor;

    public final EnumPath<spring.umc.domain.member.enums.Gender> gender = createEnum("gender", spring.umc.domain.member.enums.Gender.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> inactiveDate = createDateTime("inactiveDate", java.time.LocalDateTime.class);

    public final StringPath name = createString("name");

    public final StringPath phoneNum = createString("phoneNum");

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final ListPath<PointHistory, QPointHistory> pointHistoryList = this.<PointHistory, QPointHistory>createList("pointHistoryList", PointHistory.class, QPointHistory.class, PathInits.DIRECT2);

    public final ListPath<Qna, QQna> qnaList = this.<Qna, QQna>createList("qnaList", Qna.class, QQna.class, PathInits.DIRECT2);

    public final ListPath<spring.umc.domain.review.entity.Review, spring.umc.domain.review.entity.QReview> reviewList = this.<spring.umc.domain.review.entity.Review, spring.umc.domain.review.entity.QReview>createList("reviewList", spring.umc.domain.review.entity.Review.class, spring.umc.domain.review.entity.QReview.class, PathInits.DIRECT2);

    public final EnumPath<spring.umc.domain.member.enums.Status> status = createEnum("status", spring.umc.domain.member.enums.Status.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final ListPath<UserMission, QUserMission> userMissionList = this.<UserMission, QUserMission>createList("userMissionList", UserMission.class, QUserMission.class, PathInits.DIRECT2);

    public final ListPath<UserTerms, QUserTerms> userTermsList = this.<UserTerms, QUserTerms>createList("userTermsList", UserTerms.class, QUserTerms.class, PathInits.DIRECT2);

    public QMember(String variable) {
        this(Member.class, forVariable(variable), INITS);
    }

    public QMember(Path<? extends Member> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMember(PathMetadata metadata, PathInits inits) {
        this(Member.class, metadata, inits);
    }

    public QMember(Class<? extends Member> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.favor = inits.isInitialized("favor") ? new QFavor(forProperty("favor"), inits.get("favor")) : null;
    }

}

