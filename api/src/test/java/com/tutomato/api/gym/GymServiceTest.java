package com.tutomato.api.gym;

import com.tutomato.api.gym.api.dto.GymSaveDto;
import com.tutomato.api.gym.application.GymService;
import com.tutomato.api.gym.domain.Gym;
import com.tutomato.api.gym.domain.GymType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("loc")
public class GymServiceTest {

    @Autowired
    private GymService gymService;

    @Test
    void 등록_테스트(){
        String name = "test";
        String description = "test gym";
        String contract = "01012341234";
        String gymType = GymType.FITNESS.name();

        GymSaveDto dto = GymSaveDto.builder()
                .name(name)
                .description(description)
                .contact(contract)
                .gymType(gymType)
                .build();

        Gym gym = dto.toGym();

        Long savedId = gymService.save(gym);

        Assertions.assertNotNull(savedId);
    }
}
