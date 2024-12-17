package com.tutomato.climbinggymapi.repository;

import com.tutomato.climbinggymapi.member.domain.Member;
import com.tutomato.climbinggymapi.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 맴버_전체조회_성능테스트(){
        long before = System.currentTimeMillis();

        List<Member> all = memberRepository.findAll();
        System.out.println("member count = "+all.size());

        long after = System.currentTimeMillis();
        long diff = after - before;
        System.out.println("실행 시간: " + diff);

    }

    @Test
    public void table_join_test(){
        long before = System.currentTimeMillis();
        List<Member> joinAll = memberRepository.findAllMemberWithGym();
        System.out.println("join all size = " + joinAll.size());

        long after = System.currentTimeMillis();
        long diff = after - before;
        System.out.println("실행 시간: " + diff);
    }
}
