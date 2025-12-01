package spring.umc.domain.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.dsl.StringTemplate;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTerm is a Querydsl query type for Term
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTerm extends EntityPathBase<Term> {

    private static final long serialVersionUID = 386862153L;

    public static final QTerm term = new QTerm("term");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<spring.umc.domain.member.entity.mapping.MemberTerm, spring.umc.domain.member.entity.mapping.QMemberTerm> memberTerms = this.<spring.umc.domain.member.entity.mapping.MemberTerm, spring.umc.domain.member.entity.mapping.QMemberTerm>createList("memberTerms", spring.umc.domain.member.entity.mapping.MemberTerm.class, spring.umc.domain.member.entity.mapping.QMemberTerm.class, PathInits.DIRECT2);

    public final EnumPath<spring.umc.domain.member.enums.TermName> name = createEnum("name", spring.umc.domain.member.enums.TermName.class);

    public QTerm(String variable) {
        super(Term.class, forVariable(variable));
    }

    public QTerm(Path<? extends Term> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTerm(PathMetadata metadata) {
        super(Term.class, metadata);
    }

}

