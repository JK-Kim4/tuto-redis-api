package com.tutomato.climbinggymapi.service;

import com.tutomato.climbinggymapi.gym.domain.Gym;
import com.tutomato.climbinggymapi.gym.repository.GymRepository;
import com.tutomato.climbinggymapi.gym.service.GymService;
import com.tutomato.climbinggymapi.repository.GymRepositoryTest;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GymServiceTest {

    private static final Logger log = LoggerFactory.getLogger(GymRepositoryTest.class);

    @Autowired
    GymService service;

    @Autowired
    GymRepository repository;

    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class 체육관_조회_테스트{

        @BeforeEach
        public void insert_bulk(){
            int gymCount = 5000;

            for(int i = 1; i <= gymCount; i++ ){
                Gym gym = new Gym("test gym "+i);
                repository.save(gym);
            }
        }

        @AfterEach
        public void clear(){
            repository.deleteAll();
        }

        @Nested
        @DisplayName("체육관 조회 테스트")
        class 일반_조회_테스트{

            @Test
            @DisplayName("등록되어있는 체육관의 목록을 조회하고 실행 시간을 기록")
            public void 체육관_전체조회_테스트(){
                long before = System.currentTimeMillis();

                List<Gym> gyms = service.findAllGyms();
                log.debug("전체 데이터 크기: {}", gyms.size());

                long after = System.currentTimeMillis();
                long diff = after - before;
                log.debug("전체 조회 실행 시간: {}", diff);
            }
        }

        @Nested
        @DisplayName("체육관 조회 테스트 (Redis cache 적용)")
        class 캐시_조회_테스트{

            @BeforeEach
            public void insert_bulk(){
                int gymCount = 5000;
                for(int i = 1; i <= gymCount; i++ ){
                    Gym gym = new Gym("test gym "+i);
                    repository.save(gym);
                }

                Gym firstGym = repository.findFirstBy();
                Long firstId = firstGym.getId();
                for(Long i = firstId; i <= (firstId + 50l); i++){
                    service.findGymByIdWhitCache(i);
                }
                service.findAllGymsWithCache();
            }

            @AfterEach
            public void clear(){
                repository.deleteAll();
            }

            @Test
            @DisplayName("등록되어있는 체육관의 목록을 Redis 저장소를 참조하여 조회한다.")
            public void 체육관_전체조회_캐싱(){
                long before = System.currentTimeMillis();

                List<Gym> gyms = service.findAllGymsWithCache();
                log.debug("전체 데이터 크기: {}", gyms.size());

                long after = System.currentTimeMillis();
                long diff = after - before;
                log.debug("전체 조회 캐시적용 실행 시간: {}", diff);
            }

        }
    }
}
