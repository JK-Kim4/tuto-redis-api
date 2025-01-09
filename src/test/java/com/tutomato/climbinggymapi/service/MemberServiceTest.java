package com.tutomato.climbinggymapi.service;

import com.tutomato.climbinggymapi.member.api.dto.MemberResponseDto;
import com.tutomato.climbinggymapi.member.api.dto.MemberSaveDto;
import com.tutomato.climbinggymapi.member.service.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    MemberServiceImpl memberService;


    @Test
    public void member_create_test(){
        String email = "test@gmail.com";
        String password = "test123";
        String nickname = "test";
        String description = "test";


        MemberSaveDto memberSaveDto = MemberSaveDto.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .desc(description)
                .build();

        MemberResponseDto result = memberService.create(memberSaveDto);


        Assertions.assertEquals(email, result.getEmail());
        Assertions.assertEquals(nickname, result.getNickname());





    }
}
