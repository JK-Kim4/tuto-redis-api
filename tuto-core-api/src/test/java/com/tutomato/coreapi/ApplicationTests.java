package com.tutomato.coreapi;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tutomato.climbinggymapi.gym.domain.Gym;
import com.tutomato.climbinggymapi.gym.domain.QGym;
import com.tutomato.climbinggymapi.gym.domain.dto.GymSaveDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class ApplicationTests {

    @PersistenceContext
    EntityManager em;

    @Test
    @Transactional
    void contextLoads() {
        String name = "코알라 클라이밍";
        String location = "경기도/고영시";
        GymSaveDto dto = GymSaveDto.builder()
                .name(name)
                .location(location)
                .build();

        Gym gym = new Gym(dto);
        em.persist(gym);

        JPAQueryFactory query = new JPAQueryFactory(em);
        QGym qGym = QGym.gym;

        Gym gym1 = query.selectFrom(qGym).fetchOne();

        Assertions.assertEquals(gym1, gym);

    }

}
