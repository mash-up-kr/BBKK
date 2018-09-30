package org.seoul.kk.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTraveler is a Querydsl query type for Traveler
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTraveler extends EntityPathBase<Traveler> {

    private static final long serialVersionUID = -1267716408L;

    public static final QTraveler traveler = new QTraveler("traveler");

    public final ListPath<BookMark, QBookMark> bookMarks = this.<BookMark, QBookMark>createList("bookMarks", BookMark.class, QBookMark.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath nickname = createString("nickname");

    public final EnumPath<org.seoul.kk.entity.constant.TravelProperty> property = createEnum("property", org.seoul.kk.entity.constant.TravelProperty.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final StringPath uuid = createString("uuid");

    public QTraveler(String variable) {
        super(Traveler.class, forVariable(variable));
    }

    public QTraveler(Path<? extends Traveler> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTraveler(PathMetadata metadata) {
        super(Traveler.class, metadata);
    }

}

