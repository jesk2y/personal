package com.jeskey.bookmark.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMarkInfo is a Querydsl query type for MarkInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMarkInfo extends EntityPathBase<MarkInfo> {

    private static final long serialVersionUID = -2028702393L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMarkInfo markInfo = new QMarkInfo("markInfo");

    public final NumberPath<Long> ino = createNumber("ino", Long.class);

    public final StringPath library = createString("library");

    public final StringPath location = createString("location");

    public final QMark mark;

    public final StringPath type = createString("type");

    public QMarkInfo(String variable) {
        this(MarkInfo.class, forVariable(variable), INITS);
    }

    public QMarkInfo(Path<? extends MarkInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMarkInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMarkInfo(PathMetadata metadata, PathInits inits) {
        this(MarkInfo.class, metadata, inits);
    }

    public QMarkInfo(Class<? extends MarkInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.mark = inits.isInitialized("mark") ? new QMark(forProperty("mark"), inits.get("mark")) : null;
    }

}

