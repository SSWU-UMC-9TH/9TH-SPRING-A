package spring.umc.domain.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.dsl.StringTemplate;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFood is a Querydsl query type for Food
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFood extends EntityPathBase<Food> {

    private static final long serialVersionUID = 386454587L;

    public static final QFood food = new QFood("food");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<spring.umc.domain.member.entity.mapping.MemberFood, spring.umc.domain.member.entity.mapping.QMemberFood> memberFoods = this.<spring.umc.domain.member.entity.mapping.MemberFood, spring.umc.domain.member.entity.mapping.QMemberFood>createList("memberFoods", spring.umc.domain.member.entity.mapping.MemberFood.class, spring.umc.domain.member.entity.mapping.QMemberFood.class, PathInits.DIRECT2);

    public final EnumPath<spring.umc.domain.member.enums.FoodName> name = createEnum("name", spring.umc.domain.member.enums.FoodName.class);

    public QFood(String variable) {
        super(Food.class, forVariable(variable));
    }

    public QFood(Path<? extends Food> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFood(PathMetadata metadata) {
        super(Food.class, metadata);
    }

}

