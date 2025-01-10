package com.tutomato.climbinggymapi.member.application;

import com.tutomato.climbinggymapi.member.domain.Member;
import com.tutomato.climbinggymapi.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member findUserByIdentifier(String identifier) {
        return null;
    }

    public Member findUserByEmail(String email) {
        return null;
    }
}
