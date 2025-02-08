package com.tutomato.api.gym;

import com.tutomato.api.gym.api.dto.GymSaveDto;
import com.tutomato.api.gym.application.GymService;
import com.tutomato.api.gym.domain.Gym;
import com.tutomato.api.gym.domain.GymType;
import com.tutomato.common.exception.AlreadyExistException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GymServiceTest {

    @Autowired
    private GymService gymService;

    @Test
    void 등록_테스트(){
        String name = "test";
        String description = "test gym";
        String contract = "01012341234";
        String identifier = "abc1234000";
        String gymType = GymType.FITNESS.name();

        GymSaveDto dto = GymSaveDto.builder()
                .name(name)
                .description(description)
                .contact(contract)
                .gymType(gymType)
                .identifier(identifier)
                .build();

        Long savedId = gymService.save(dto);
        Gym gym = gymService.findById(savedId);
        Assertions.assertNotNull(savedId);
        Assertions.assertEquals(identifier, gym.getIdentifier());
    }

    @Test
    void 식별자_중복_테스트(){
        String name = "test";
        String description = "test gym";
        String contract = "01012341234";
        String identifier = "abc1234000";
        String gymType = GymType.FITNESS.name();

        GymSaveDto dto = GymSaveDto.builder()
                .name(name)
                .description(description)
                .contact(contract)
                .gymType(gymType)
                .identifier(identifier)
                .build();

        gymService.save(dto);

        GymSaveDto dto2 = GymSaveDto.builder()
                .name(name)
                .description(description)
                .contact(contract)
                .gymType(gymType)
                .identifier(identifier)
                .build();


        Assertions.assertThrows(AlreadyExistException.class, () -> gymService.save(dto2));
    }
}
