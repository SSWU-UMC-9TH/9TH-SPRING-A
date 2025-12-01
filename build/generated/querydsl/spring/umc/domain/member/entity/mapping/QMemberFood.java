package spring.umc.domain.member.entity.mapping;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.dsl.StringTemplate;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberFood is a Querydsl query type for MemberFood
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberFood extends EntityPathBase<MemberFood> {

    private static final long serialVersionUID = 1410508885L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberFood memberFood = new QMemberFood("memberFood");

    public final spring.umc.domain.member.entity.QFood food;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final spring.umc.domain.member.entity.QMember member;

    public QMemberFood(String variable) {
        this(MemberFood.class, forVariable(variable), INITS);
    }

    public QMemberFood(Path<? extends MemberFood> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberFood(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberFood(PathMetadata metadata, PathInits inits) {
        this(MemberFood.class, metadata, inits);
    }

    public QMemberFood(Class<? extends MemberFood> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.food = inits.isInitialized("food") ? new spring.umc.domain.member.entity.QFood(forProperty("food")) : null;
        this.member = inits.isInitialized("member") ? new spring.umc.domain.member.entity.QMember(forProperty("member")) : null;
    }

}

