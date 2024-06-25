package com.jeskey.bookmark.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMark is a Querydsl query type for Mark
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMark extends EntityPathBase<Mark> {

    private static final long serialVersionUID = 362594553L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMark mark = new QMark("mark");

    public final QBook book;

    public final EnumPath<FlagYN> isRead = createEnum("isRead", FlagYN.class);

    public final QMember member;

    public final NumberPath<Long> mno = createNumber("mno", Long.class);

    public QMark(String variable) {
        this(Mark.class, forVariable(variable), INITS);
    }

    public QMark(Path<? extends Mark> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMark(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMark(PathMetadata metadata, PathInits inits) {
        this(Mark.class, metadata, inits);
    }

    public QMark(Class<? extends Mark> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.book = inits.isInitialized("book") ? new QBook(forProperty("book")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

