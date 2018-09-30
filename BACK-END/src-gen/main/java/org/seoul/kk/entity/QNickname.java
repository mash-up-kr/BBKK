package org.seoul.kk.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QNickname is a Querydsl query type for Nickname
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QNickname extends EntityPathBase<Nickname> {

    private static final long serialVersionUID = 1810082287L;

    public static final QNickname nickname = new QNickname("nickname");

    public final EnumPath<org.seoul.kk.entity.constant.Classification> classification = createEnum("classification", org.seoul.kk.entity.constant.Classification.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath word = createString("word");

    public QNickname(String variable) {
        super(Nickname.class, forVariable(variable));
    }

    public QNickname(Path<? extends Nickname> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNickname(PathMetadata metadata) {
        super(Nickname.class, metadata);
    }

}

