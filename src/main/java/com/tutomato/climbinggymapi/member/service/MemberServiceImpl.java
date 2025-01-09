package com.tutomato.climbinggymapi.member.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutomato.climbinggymapi.common.exception.MemberNotFoundException;
import com.tutomato.climbinggymapi.common.interceptor.VerifyUserFilter;
import com.tutomato.climbinggymapi.common.jwt.Jwt;
import com.tutomato.climbinggymapi.common.jwt.JwtProvider;
import com.tutomato.climbinggymapi.member.api.dto.MemberResponseDto;
import com.tutomato.climbinggymapi.member.api.dto.MemberSaveDto;
import com.tutomato.climbinggymapi.member.domain.AuthenticateMember;
import com.tutomato.climbinggymapi.member.domain.Member;
import com.tutomato.climbinggymapi.member.domain.MemberRole;
import com.tutomato.climbinggymapi.member.domain.Role;
import com.tutomato.climbinggymapi.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;
    private final ObjectMapper objectMapper;

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


    @Transactional
    public Jwt refreshToken(String refreshToken){
        try{
            // 유요한 토큰인지 확인
            jwtProvider.parseToken(refreshToken);
            Member member = memberRepository.findByRefreshToken(refreshToken)
                    .orElseThrow(MemberNotFoundException::new);
            List<MemberRole> memberRoles = memberRepository.findMemberRolesByEmail(member.getEmail());

            HashMap<String, Object> claims = new HashMap<>();
            AuthenticateMember authenticateMember = new AuthenticateMember(member.getEmail(), memberRoles.stream().map(MemberRole::getRole).collect(Collectors.toSet()));
            String authenticateUserJson = objectMapper.writeValueAsString(authenticateMember);
            claims.put(VerifyUserFilter.AUTHENTICATE_MEMBER,authenticateUserJson);
            Jwt jwt = jwtProvider.createJwt(claims);
            updateRefreshToken(member.getEmail(),jwt.getRefreshToken());
            return jwt;
        } catch (Exception e){
            return null;
        }
    }
}
