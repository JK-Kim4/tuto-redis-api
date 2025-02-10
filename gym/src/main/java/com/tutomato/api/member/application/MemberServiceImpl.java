package com.tutomato.api.member.application;

import com.tutomato.api.gym.domain.Gym;
import com.tutomato.api.gym.domain.GymType;
import com.tutomato.api.gym.repository.GymRepository;
import com.tutomato.api.member.api.dto.MemberSaveDto;
import com.tutomato.api.member.domain.Member;
import com.tutomato.api.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final GymRepository gymRepository;

    @Override
    public Long save(MemberSaveDto dto) {

        Member member = dto.toMember();
        memberRepository.save(member);

        this.registerMemberToGyn(
                gymRepository.findById(dto.getGymId()).orElseThrow(),
                member);

        return member.getId();
    }

    @Override
    public Member findById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow();
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    private void registerMemberToGyn(Gym gym, Member member){
        gym.registerMember(member);
        member.setGym(gym);
    }
}
