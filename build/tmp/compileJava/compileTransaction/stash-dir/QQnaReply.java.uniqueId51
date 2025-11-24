package spring.umc.domain.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.dsl.StringTemplate;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQnaReply is a Querydsl query type for QnaReply
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQnaReply extends EntityPathBase<QnaReply> {

    private static final long serialVersionUID = 718474435L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQnaReply qnaReply = new QQnaReply("qnaReply");

    public final spring.umc.global.QBaseEntity _super = new spring.umc.global.QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QQna qna;

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QQnaReply(String variable) {
        this(QnaReply.class, forVariable(variable), INITS);
    }

    public QQnaReply(Path<? extends QnaReply> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQnaReply(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQnaReply(PathMetadata metadata, PathInits inits) {
        this(QnaReply.class, metadata, inits);
    }

    public QQnaReply(Class<? extends QnaReply> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.qna = inits.isInitialized("qna") ? new QQna(forProperty("qna"), inits.get("qna")) : null;
    }

}

