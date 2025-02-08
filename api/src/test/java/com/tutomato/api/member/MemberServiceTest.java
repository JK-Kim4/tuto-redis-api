package com.tutomato.api.member;

import com.tutomato.api.gym.api.dto.GymSaveDto;
import com.tutomato.api.gym.application.GymService;
import com.tutomato.api.gym.domain.Gym;
import com.tutomato.api.gym.domain.GymType;
import com.tutomato.api.member.api.dto.MemberSaveDto;
import com.tutomato.api.member.application.MemberService;
import com.tutomato.api.member.domain.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    GymService gymService;

    @Test
    void 맴버_등록_테스트(){
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

        Long gymId = gymService.save(dto);


        String nickname = "tester";
        String mem_contract = "1234111";

        MemberSaveDto memberSaveDto = MemberSaveDto.builder()
                .gymId(gymId)
                .contract(mem_contract)
                .nickname(nickname)
                .build();

        Long memberId = memberService.save(memberSaveDto);

        Member member = memberService.findById(memberId);
        Gym gym = gymService.findById(gymId);

        Assertions.assertEquals(gym.getId(), member.getGym().getId());
    }


}
