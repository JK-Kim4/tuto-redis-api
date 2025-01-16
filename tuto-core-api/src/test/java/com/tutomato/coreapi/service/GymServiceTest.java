package com.tutomato.coreapi.service;

import com.tutomato.climbinggymapi.TestValue;
import com.tutomato.climbinggymapi.gym.domain.Gym;
import com.tutomato.climbinggymapi.gym.domain.Gyms;
import com.tutomato.climbinggymapi.gym.domain.dto.GymSaveDto;
import com.tutomato.climbinggymapi.gym.repository.GymJpaRepository;
import com.tutomato.climbinggymapi.gym.service.GymService;
import com.tutomato.climbinggymapi.member.domain.Member;
import com.tutomato.climbinggymapi.member.repository.MemberRepository;
import com.tutomato.climbinggymapi.repository.GymJpaRepositoryTest;
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

    private static final Logger log = LoggerFactory.getLogger(GymJpaRepositoryTest.class);

    @Autowired
    GymService service;

    @Autowired
    GymJpaRepository repository;

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
                Gym gym = new Gym("test gym" + i);
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

            log.debug("[01_체육관 정보 조회] 전체 데이터 크기: {}", gyms.size());
            log.debug("[01_체육관 정보 조회] 전체 조회 실행 시간: {}", diff);
        }

        @Test
        @DisplayName("02_체육관 정보 조회 캐시적용")
        public void test_c(){
            long before = System.currentTimeMillis();

            Gyms gyms = service.findAllGymsWithCache();

            long after = System.currentTimeMillis();
            long diff = after - before;
            log.debug("[02_체육관 정보 조회 캐시적용] 전체 데이터 수: {}", gyms.getGyms().size());
            log.debug("[02_체육관 정보 조회 캐시적용] 전체 조회 캐시 최초 실행 시간: {}", diff);
        }

        @Test
        @DisplayName("03_체육관 전체 정보 캐시조회")
        public void test_d(){
            long before = System.currentTimeMillis();

            Gyms gyms = service.findAllGymsWithCache();

            long after = System.currentTimeMillis();
            long diff = after - before;
            log.debug("[03_체육관 전체 정보 캐시조회] 전체 데이터 수: {}", gyms.getGyms().size());
            log.debug("[03_체육관 전체 정보 캐시조회] 전체 조회 캐시 적용 실행 시간: {}", diff);
        }

        @Test
        @DisplayName("04_체육관 전체 정보 캐시조회")
        public void test_e(){
            long before = System.currentTimeMillis();

            Gyms gyms = service.findAllGymsWithCache();

            long after = System.currentTimeMillis();
            long diff = after - before;
            log.debug("[04_체육관 전체 정보 캐시조회] 전체 데이터 수: {}", gyms.getGyms().size());
            log.debug("[04_체육관 전체 정보 캐시조회] 전체 조회 캐시 적용 실행 시간: {}", diff);
        }
    }

    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class 체육관_캐시_테스트{

        private Long gymId = null;
        private String originalGymName = "코알라 클라이밍";
        private String originalGymlocation = "경기도/고영시";

        @BeforeEach
        @Transactional
        public void save_data(){

            GymSaveDto dto = GymSaveDto.builder()
                    .name(this.originalGymName)
                    .location(this.originalGymlocation)
                    .build();

            this.gymId = service.save(dto);
        }

        @Test
        @DisplayName("00_데이터_조회_캐시등록")
        public void 데이터_조회_캐시등록(){
            Gym gym = service.findGymByIdWhitCache(this.gymId);

            Assertions.assertEquals(gym.getName(), this.originalGymName);

            //evict cache
            //gym.updateGymInformation(renewDto);
        }

        @Test
        @DisplayName("01_데이터_조회_캐시_조회")
        public void 단건조회_캐시_조회_테스트(){
            //현재 DB에 값이 존재하지 않음 create-drop
            Long gymId = 1l;
            Gym gym = service.findGymByIdWhitCache(gymId);
            //캐시 데이터를 조회하여 정상응답
            Assertions.assertEquals(gym.getName(), this.originalGymName);
        }

        @Test
        @DisplayName("03_엔티티_업데이트_캐시삭제_테스트")
        public void 엔티티_업데이트_캐시삭제_테스트(){
            String renewName = "더클라임";
            String renewLocation = "서울시 마포구";
            GymSaveDto renewDto = GymSaveDto.builder()
                    .id(this.gymId)
                    .name(renewName)
                    .location(renewLocation)
                    .build();

            Long gymId = service.update(renewDto);

            Gym gym = service.findGymByIdWhitCache(gymId);

            Assertions.assertEquals(gym.getName(), renewName);
        }

    }
}
