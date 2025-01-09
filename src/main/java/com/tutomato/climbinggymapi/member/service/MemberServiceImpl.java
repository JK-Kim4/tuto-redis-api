package com.tutomato.climbinggymapi.member.service;

import com.tutomato.climbinggymapi.common.exception.MemberNotFoundException;
import com.tutomato.climbinggymapi.member.api.dto.MemberResponseDto;
import com.tutomato.climbinggymapi.member.api.dto.MemberSaveDto;
import com.tutomato.climbinggymapi.member.domain.MemberRole;
import com.tutomato.climbinggymapi.member.domain.Role;
import com.tutomato.climbinggymapi.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public MemberResponseDto create(MemberSaveDto dto) {
        return new MemberResponseDto(memberRepository.save(dto.toEntity()));
    }

    @Override
    public MemberResponseDto findById(Long id) {
        return new MemberResponseDto(memberRepository.findById(id)
                .orElseThrow(MemberNotFoundException::new));
    }

    @Override
    public MemberResponseDto findByEmail(String email) {
        return new MemberResponseDto(memberRepository.findByEmail(email)
                .orElseThrow(MemberNotFoundException::new));
    }

    @Override
    public void updateRefreshToken(String email, String refreshToken) {
        memberRepository.findByEmail(email).
                ifPresent(member -> member.updateRefreshToken(refreshToken));
    }

    @Override
    public MemberRole addMemberRole(String email, Role role) {
        return null;
    }

    @Override
    public Set<Role> getMemberRolesByEmail(String email) {
        return memberRepository.findMemberRolesByEmail(email).stream().map(MemberRole::getRole).collect(Collectors.toSet());
    }
}
