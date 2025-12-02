package spring.umc.domain.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.dsl.StringTemplate;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQnaImage is a Querydsl query type for QnaImage
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQnaImage extends EntityPathBase<QnaImage> {

    private static final long serialVersionUID = 710386484L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQnaImage qnaImage = new QQnaImage("qnaImage");

    public final spring.umc.global.QBaseEntity _super = new spring.umc.global.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath meta = createString("meta");

    public final QQna qna;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath url = createString("url");

    public QQnaImage(String variable) {
        this(QnaImage.class, forVariable(variable), INITS);
    }

    public QQnaImage(Path<? extends QnaImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQnaImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQnaImage(PathMetadata metadata, PathInits inits) {
        this(QnaImage.class, metadata, inits);
    }

    public QQnaImage(Class<? extends QnaImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.qna = inits.isInitialized("qna") ? new QQna(forProperty("qna"), inits.get("qna")) : null;
    }

}

