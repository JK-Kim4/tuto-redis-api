package com.tutomato.climbinggymapi.domain;

import com.tutomato.climbinggymapi.gym.domain.Gym;
import com.tutomato.climbinggymapi.gym.domain.dto.GymSaveDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

public class GymTest {

    @Test
    public void 도메인_빌드_디티오_테스트(){
        String name = "코알라 클라이밍";
        String location = "경기도/고영시";
        GymSaveDto dto = GymSaveDto.builder()
                .name(name)
                .location(location)
                .build();

        Gym gym = new Gym(dto);
        Assertions.assertNotNull(gym);
        Assertions.assertEquals(name, gym.getName());
    }


}
