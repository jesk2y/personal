package com.jeskey.bookmark.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 564554182L;

    public static final QMember member = new QMember("member1");

    public final EnumPath<FlagYN> activated = createEnum("activated", FlagYN.class);

    public final StringPath email = createString("email");

    public final DateTimePath<java.time.LocalDateTime> joinDate = createDateTime("joinDate", java.time.LocalDateTime.class);

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final SetPath<MemberRole, EnumPath<MemberRole>> roleSet = this.<MemberRole, EnumPath<MemberRole>>createSet("roleSet", MemberRole.class, EnumPath.class, PathInits.DIRECT2);

    public final EnumPath<FlagYN> sso = createEnum("sso", FlagYN.class);

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

