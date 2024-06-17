package org.jeskey.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoardAttach is a Querydsl query type for BoardAttach
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardAttach extends EntityPathBase<BoardAttach> {

    private static final long serialVersionUID = -450194306L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoardAttach boardAttach = new QBoardAttach("boardAttach");

    public final QBoard board;

    public final StringPath date = createString("date");

    public final StringPath file_name = createString("file_name");

    public final NumberPath<Integer> ord = createNumber("ord", Integer.class);

    public final StringPath uuid = createString("uuid");

    public QBoardAttach(String variable) {
        this(BoardAttach.class, forVariable(variable), INITS);
    }

    public QBoardAttach(Path<? extends BoardAttach> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoardAttach(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoardAttach(PathMetadata metadata, PathInits inits) {
        this(BoardAttach.class, metadata, inits);
    }

    public QBoardAttach(Class<? extends BoardAttach> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new QBoard(forProperty("board")) : null;
    }

}

