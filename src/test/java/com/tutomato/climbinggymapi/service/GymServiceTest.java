package com.tutomato.climbinggymapi.service;

import com.tutomato.climbinggymapi.TestValue;
import com.tutomato.climbinggymapi.gym.domain.Gym;
import com.tutomato.climbinggymapi.gym.domain.Gyms;
import com.tutomato.climbinggymapi.gym.repository.GymRepository;
import com.tutomato.climbinggymapi.gym.service.GymService;
import com.tutomato.climbinggymapi.member.domain.Member;
import com.tutomato.climbinggymapi.member.repository.MemberRepository;
import com.tutomato.climbinggymapi.repository.GymRepositoryTest;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GymServiceTest {

    private static final Logger log = LoggerFactory.getLogger(GymRepositoryTest.class);

    @Autowired
    GymService service;

    @Autowired
    GymRepository repository;

    @Autowired
    MemberRepository memberRepository;

    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class 체육관_조회_테스트{

        @Test
        @Rollback(value = false)
        @DisplayName("00_벌크 데이터 등록")
        public void test_a(){
            int gymCount = 30000;
            for (int i = 1; i <= gymCount; i++) {
                Gym gym = new Gym("test gym" +i);
                repository.save(gym);
                for(int j = 0; j < 2; j++){
                    Member member = new Member("test member", gym.getName(), TestValue.IPSUM);
                    memberRepository.save(member);
                }
            }
        }


        @Test
        @DisplayName("01_체육관 정보 조회")
        public void test_b(){
            long before = System.currentTimeMillis();

            List<Gym> gyms = service.findAllGyms();

            long after = System.currentTimeMillis();
            long diff = after - before;

            log.debug("전체 데이터 크기: {}", gyms.size());
            log.debug("전체 조회 실행 시간: {}", diff);
        }

        @Test
        @DisplayName("02_체육관 정보 조회 캐시적용")
        public void test_c(){
            long before = System.currentTimeMillis();

            Gyms gyms = service.findAllGymsWithCache();

            long after = System.currentTimeMillis();
            long diff = after - before;
            log.debug("전체 데이터 수: {}", gyms.getGyms().size());
            log.debug("전체 조회 캐시 최초 실행 시간: {}", diff);
        }

        @Test
        @DisplayName("03_체육관 전체 정보 캐시조회")
        public void test_d(){
            long before = System.currentTimeMillis();

            Gyms gyms = service.findAllGymsWithCache();

            long after = System.currentTimeMillis();
            long diff = after - before;
            log.debug("전체 데이터 수: {}", gyms.getGyms().size());
            log.debug("전체 조회 캐시 적용 실행 시간: {}", diff);
        }

        @Test
        @DisplayName("04_체육관 전체 정보 캐시조회")
        public void test_e(){
            long before = System.currentTimeMillis();

            Gyms gyms = service.findAllGymsWithCache();

            long after = System.currentTimeMillis();
            long diff = after - before;
            log.debug("전체 데이터 수: {}", gyms.getGyms().size());
            log.debug("전체 조회 캐시 적용 실행 시간: {}", diff);
        }

        @Test
        @DisplayName("05_체육관 전체 정보 캐시조회")
        public void test_f(){
            long before = System.currentTimeMillis();

            Gyms gyms = service.findAllGymsWithCache();

            long after = System.currentTimeMillis();
            long diff = after - before;
            log.debug("전체 데이터 수: {}", gyms.getGyms().size());
            log.debug("전체 조회 캐시 적용 실행 시간: {}", diff);
        }
    }
}
