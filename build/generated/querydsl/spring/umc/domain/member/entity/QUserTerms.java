package spring.umc.domain.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.dsl.StringTemplate;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserTerms is a Querydsl query type for UserTerms
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserTerms extends EntityPathBase<UserTerms> {

    private static final long serialVersionUID = -1857704257L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserTerms userTerms = new QUserTerms("userTerms");

    public final spring.umc.global.QBaseEntity _super = new spring.umc.global.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isChecked = createBoolean("isChecked");

    public final QMember member;

    public final QTerms terms;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QUserTerms(String variable) {
        this(UserTerms.class, forVariable(variable), INITS);
    }

    public QUserTerms(Path<? extends UserTerms> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserTerms(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserTerms(PathMetadata metadata, PathInits inits) {
        this(UserTerms.class, metadata, inits);
    }

    public QUserTerms(Class<? extends UserTerms> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
        this.terms = inits.isInitialized("terms") ? new QTerms(forProperty("terms")) : null;
    }

}

