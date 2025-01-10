package com.tutomato.climbinggymapi.member.application;

import com.tutomato.climbinggymapi.member.domain.Member;
import com.tutomato.climbinggymapi.member.domain.MemberPrincipal;
import com.tutomato.climbinggymapi.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomMemberDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;


    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Member member = memberRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new UsernameNotFoundException(userId));
        return new MemberPrincipal(member);
    }
}
