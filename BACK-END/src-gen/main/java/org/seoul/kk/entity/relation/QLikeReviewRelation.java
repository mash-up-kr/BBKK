package org.seoul.kk.entity.relation;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLikeReviewRelation is a Querydsl query type for LikeReviewRelation
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLikeReviewRelation extends EntityPathBase<LikeReviewRelation> {

    private static final long serialVersionUID = 1205574460L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLikeReviewRelation likeReviewRelation = new QLikeReviewRelation("likeReviewRelation");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> likeAt = createDateTime("likeAt", java.time.LocalDateTime.class);

    public final org.seoul.kk.entity.QReview review;

    public final org.seoul.kk.entity.QTraveler traveler;

    public QLikeReviewRelation(String variable) {
        this(LikeReviewRelation.class, forVariable(variable), INITS);
    }

    public QLikeReviewRelation(Path<? extends LikeReviewRelation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLikeReviewRelation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLikeReviewRelation(PathMetadata metadata, PathInits inits) {
        this(LikeReviewRelation.class, metadata, inits);
    }

    public QLikeReviewRelation(Class<? extends LikeReviewRelation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.review = inits.isInitialized("review") ? new org.seoul.kk.entity.QReview(forProperty("review"), inits.get("review")) : null;
        this.traveler = inits.isInitialized("traveler") ? new org.seoul.kk.entity.QTraveler(forProperty("traveler")) : null;
    }

}

