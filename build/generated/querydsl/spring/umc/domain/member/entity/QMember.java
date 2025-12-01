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

    public static final QMember member = new QMember("member1");

    public final spring.umc.global.entity.QBaseEntity _super = new spring.umc.global.entity.QBaseEntity(this);

    public final EnumPath<spring.umc.domain.store.enums.Address> address = createEnum("address", spring.umc.domain.store.enums.Address.class);

    public final DatePath<java.time.LocalDate> birth = createDate("birth", java.time.LocalDate.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final StringPath detail_address = createString("detail_address");

    public final StringPath email = createString("email");

    public final EnumPath<spring.umc.domain.member.enums.Gender> gender = createEnum("gender", spring.umc.domain.member.enums.Gender.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<spring.umc.domain.member.entity.mapping.MemberFood, spring.umc.domain.member.entity.mapping.QMemberFood> memberFoods = this.<spring.umc.domain.member.entity.mapping.MemberFood, spring.umc.domain.member.entity.mapping.QMemberFood>createList("memberFoods", spring.umc.domain.member.entity.mapping.MemberFood.class, spring.umc.domain.member.entity.mapping.QMemberFood.class, PathInits.DIRECT2);

    public final ListPath<spring.umc.domain.member.entity.mapping.MemberTerm, spring.umc.domain.member.entity.mapping.QMemberTerm> memberTerms = this.<spring.umc.domain.member.entity.mapping.MemberTerm, spring.umc.domain.member.entity.mapping.QMemberTerm>createList("memberTerms", spring.umc.domain.member.entity.mapping.MemberTerm.class, spring.umc.domain.member.entity.mapping.QMemberTerm.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final EnumPath<spring.umc.global.auth.enums.SocialType> socialType = createEnum("socialType", spring.umc.global.auth.enums.SocialType.class);

    public final StringPath socialUid = createString("socialUid");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

