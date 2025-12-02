package spring.umc.domain.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.dsl.StringTemplate;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFavor is a Querydsl query type for Favor
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFavor extends EntityPathBase<Favor> {

    private static final long serialVersionUID = -905219583L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFavor favor = new QFavor("favor");

    public final spring.umc.global.QBaseEntity _super = new spring.umc.global.QBaseEntity(this);

    public final BooleanPath asianFood = createBoolean("asianFood");

    public final BooleanPath chinese = createBoolean("chinese");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final BooleanPath dessert = createBoolean("dessert");

    public final BooleanPath fastFood = createBoolean("fastFood");

    public final BooleanPath grilledMeat = createBoolean("grilledMeat");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath japanese = createBoolean("japanese");

    public final BooleanPath korean = createBoolean("korean");

    public final BooleanPath lateNight = createBoolean("lateNight");

    public final QMember member;

    public final BooleanPath snack = createBoolean("snack");

    public final BooleanPath sushi = createBoolean("sushi");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final BooleanPath western = createBoolean("western");

    public QFavor(String variable) {
        this(Favor.class, forVariable(variable), INITS);
    }

    public QFavor(Path<? extends Favor> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFavor(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFavor(PathMetadata metadata, PathInits inits) {
        this(Favor.class, metadata, inits);
    }

    public QFavor(Class<? extends Favor> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
    }

}

