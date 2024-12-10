package com.tutomato.climbinggymapi.repository;

import com.tutomato.climbinggymapi.gym.domain.Gym;
import com.tutomato.climbinggymapi.gym.domain.dto.GymSaveDto;
import com.tutomato.climbinggymapi.gym.presentation.GymRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;

@SpringBootTest
public class GymRepositoryTest {

    @Autowired
    GymRepository gymRepository;

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


        gymRepository.save(gym1);
        gymRepository.save(gym2);

        Gym gym3 = gymRepository.findById(gym1.getId()).get();
        Gym gym4 = gymRepository.findById(gym2.getId()).get();

        Assertions.assertEquals(gym1.getId(), gym3.getId());
        Assertions.assertEquals(gym2.getId(), gym4.getId());
    }
}
