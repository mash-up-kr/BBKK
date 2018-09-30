package org.seoul.kk.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPlayLand is a Querydsl query type for PlayLand
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPlayLand extends EntityPathBase<PlayLand> {

    private static final long serialVersionUID = -677062464L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPlayLand playLand = new QPlayLand("playLand");

    public final StringPath category = createString("category");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final StringPath position = createString("position");

    public final NumberPath<Long> reviewCnt = createNumber("reviewCnt", Long.class);

    public final EnumPath<org.seoul.kk.entity.constant.Season> season = createEnum("season", org.seoul.kk.entity.constant.Season.class);

    public final StringPath title = createString("title");

    public final QTraveler traveler;

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QPlayLand(String variable) {
        this(PlayLand.class, forVariable(variable), INITS);
    }

    public QPlayLand(Path<? extends PlayLand> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPlayLand(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPlayLand(PathMetadata metadata, PathInits inits) {
        this(PlayLand.class, metadata, inits);
    }

    public QPlayLand(Class<? extends PlayLand> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.traveler = inits.isInitialized("traveler") ? new QTraveler(forProperty("traveler")) : null;
    }

}

