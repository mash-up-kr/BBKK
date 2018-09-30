package org.seoul.kk.entity.history;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QNicknameHistory is a Querydsl query type for NicknameHistory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QNicknameHistory extends EntityPathBase<NicknameHistory> {

    private static final long serialVersionUID = 854573899L;

    public static final QNicknameHistory nicknameHistory = new QNicknameHistory("nicknameHistory");

    public final StringPath adjective = createString("adjective");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath noun = createString("noun");

    public QNicknameHistory(String variable) {
        super(NicknameHistory.class, forVariable(variable));
    }

    public QNicknameHistory(Path<? extends NicknameHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNicknameHistory(PathMetadata metadata) {
        super(NicknameHistory.class, metadata);
    }

}

