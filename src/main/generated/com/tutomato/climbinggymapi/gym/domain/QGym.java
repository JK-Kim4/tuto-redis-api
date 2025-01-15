package com.tutomato.climbinggymapi.gym.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QGym is a Querydsl query type for Gym
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGym extends EntityPathBase<Gym> {

    private static final long serialVersionUID = -1097480176L;

    public static final QGym gym = new QGym("gym");

    public final StringPath address = createString("address");

    public final TimePath<java.time.LocalTime> closeTime = createTime("closeTime", java.time.LocalTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isOpen = createBoolean("isOpen");

    public final StringPath location = createString("location");

    public final StringPath name = createString("name");

    public final TimePath<java.time.LocalTime> openTime = createTime("openTime", java.time.LocalTime.class);

    public final StringPath phoneNumber = createString("phoneNumber");

    public QGym(String variable) {
        super(Gym.class, forVariable(variable));
    }

    public QGym(Path<? extends Gym> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGym(PathMetadata metadata) {
        super(Gym.class, metadata);
    }

}

