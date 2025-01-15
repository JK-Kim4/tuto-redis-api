package com.tutomato.climbinggymapi.member.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -832048548L;

    public static final QMember member = new QMember("member1");

    public final StringPath desc = createString("desc");

    public final StringPath gymName = createString("gymName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath realName = createString("realName");

    public final StringPath testString1 = createString("testString1");

    public final StringPath testString2 = createString("testString2");

    public final StringPath testString3 = createString("testString3");

    public final TimePath<java.time.LocalTime> testTime1 = createTime("testTime1", java.time.LocalTime.class);

    public final TimePath<java.time.LocalTime> testTime2 = createTime("testTime2", java.time.LocalTime.class);

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

