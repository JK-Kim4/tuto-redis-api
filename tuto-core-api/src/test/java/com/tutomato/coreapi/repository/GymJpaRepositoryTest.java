package com.tutomato.coreapi.repository;

import com.tutomato.climbinggymapi.TestValue;
import com.tutomato.climbinggymapi.gym.domain.Gym;
import com.tutomato.climbinggymapi.gym.domain.dto.GymSaveDto;
import com.tutomato.climbinggymapi.gym.repository.GymJpaRepository;
import com.tutomato.climbinggymapi.member.domain.Member;
import com.tutomato.climbinggymapi.member.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;

@SpringBootTest
public class GymJpaRepositoryTest {

    private static final Logger log = LoggerFactory.getLogger(GymJpaRepositoryTest.class);
    @Autowired
    GymJpaRepository gymJpaRepository;

    @Autowired
    MemberRepository memberRepository;

    @AfterEach
    public void after(){
        gymJpaRepository.deleteAll();
        memberRepository.deleteAll();
    }

    @BeforeEach
    public void bulk_data_insert(){
        //join 성능 비교를 위한 dummy data insert
        int gymCount = 5000;

        for(int i = 1; i <= gymCount; i++ ){
            Gym gym = new Gym("test gym "+i);
            gymJpaRepository.save(gym);
            for(int j = 0; j < 2; j++){
                Member member = new Member("member "+j, gym.getName(), TestValue.IPSUM);
                memberRepository.save(member);
            }
        }
    }


    @Test
    public void 체육관_전체조회_성능테스트(){
        long before = System.currentTimeMillis();

        List<Gym> all = gymJpaRepository.findAll();
        System.out.println("gym count = " + all.size());

        long after = System.currentTimeMillis();
        long diff = after - before;
        System.out.println("실행 시간: " + diff);
    }



    @Test
    @Transactional
    public void basicCRUD(){
        GymSaveDto gymDto1 = GymSaveDto.builder()
                .name("코알라")
                .location("경기도 고양시")
                .phoneNumber("01012341234")
                .isOpen(true)
                .openTime(LocalTime.now())
                .closeTime(LocalTime.now())
                .build();

        GymSaveDto gymDto2 = GymSaveDto.builder()
                .name("더클라임")
                .location("서울시 마포구")
                .phoneNumber("01043214321")
                .isOpen(true)
                .openTime(LocalTime.now())
                .closeTime(LocalTime.now())
                .build();

        Gym gym1 = new Gym(gymDto1);
        Gym gym2 = new Gym(gymDto2);


        gymJpaRepository.save(gym1);
        gymJpaRepository.save(gym2);

        Gym gym3 = gymJpaRepository.findById(gym1.getId()).get();
        Gym gym4 = gymJpaRepository.findById(gym2.getId()).get();

        Assertions.assertEquals(gym1.getId(), gym3.getId());
        Assertions.assertEquals(gym2.getId(), gym4.getId());
    }
}
