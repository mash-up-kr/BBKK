package org.seoul.kk.entity.relation;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLikePlayLandRelation is a Querydsl query type for LikePlayLandRelation
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLikePlayLandRelation extends EntityPathBase<LikePlayLandRelation> {

    private static final long serialVersionUID = -1566223837L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLikePlayLandRelation likePlayLandRelation = new QLikePlayLandRelation("likePlayLandRelation");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> likeAt = createDateTime("likeAt", java.time.LocalDateTime.class);

    public final org.seoul.kk.entity.QPlayLand playLand;

    public final org.seoul.kk.entity.QTraveler traveler;

    public QLikePlayLandRelation(String variable) {
        this(LikePlayLandRelation.class, forVariable(variable), INITS);
    }

    public QLikePlayLandRelation(Path<? extends LikePlayLandRelation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLikePlayLandRelation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLikePlayLandRelation(PathMetadata metadata, PathInits inits) {
        this(LikePlayLandRelation.class, metadata, inits);
    }

    public QLikePlayLandRelation(Class<? extends LikePlayLandRelation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.playLand = inits.isInitialized("playLand") ? new org.seoul.kk.entity.QPlayLand(forProperty("playLand"), inits.get("playLand")) : null;
        this.traveler = inits.isInitialized("traveler") ? new org.seoul.kk.entity.QTraveler(forProperty("traveler")) : null;
    }

}

