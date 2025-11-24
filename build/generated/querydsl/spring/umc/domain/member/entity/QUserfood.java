package spring.umc.domain.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.dsl.StringTemplate;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserfood is a Querydsl query type for Userfood
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserfood extends EntityPathBase<Userfood> {

    private static final long serialVersionUID = -613569530L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserfood userfood = new QUserfood("userfood");

    public final QFood food;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public QUserfood(String variable) {
        this(Userfood.class, forVariable(variable), INITS);
    }

    public QUserfood(Path<? extends Userfood> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserfood(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserfood(PathMetadata metadata, PathInits inits) {
        this(Userfood.class, metadata, inits);
    }

    public QUserfood(Class<? extends Userfood> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.food = inits.isInitialized("food") ? new QFood(forProperty("food")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
    }

}

