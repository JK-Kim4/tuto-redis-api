package com.tutomato.climbinggymapi.service;

import com.tutomato.climbinggymapi.gym.domain.Gym;
import com.tutomato.climbinggymapi.gym.domain.dto.GymSaveDto;
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

    @BeforeAll
    public void insert_bulk(){
        int gymCount = 5000;

        for(int i = 1; i <= gymCount; i++ ){
            GymSaveDto dto = GymSaveDto.builder()
                    .name("gym"+i)
                    .build();
            service.save(dto);
        }
    }

//    @AfterAll
//    void clean_gym(){service.deleteAll();}

    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class 체육관_조회_테스트{

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
            public void set_cache(){
                Long count = 50l;
                for(Long i = 1l; i <= count; i++){
                    service.findGymByIdWhitCache(i);
                }

                service.findAllGymsWithCache();
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
