package com.tutomato.climbinggymapi.member.service;

import com.tutomato.climbinggymapi.member.api.dto.MemberResponseDto;
import com.tutomato.climbinggymapi.member.api.dto.MemberSaveDto;
import com.tutomato.climbinggymapi.member.domain.MemberRole;
import com.tutomato.climbinggymapi.member.domain.Role;

import java.util.Set;


public interface MemberService {


    /**
     * 회원 가입 요청 시 신규 회원 정보를 등록합니다.
     * */
    MemberResponseDto create(MemberSaveDto dto);

    /**
     * 회원 고유 번호로 회원을 조회합니다.
     * */
    MemberResponseDto findById(Long id);

    /**
     * 회원 이메일로 회원을 조회합니다.
     * */
    MemberResponseDto findByEmail(String email);

    /**
     * Refresh token을 갱신합니다.
     * */
    void updateRefreshToken(String email, String refreshToken);

    MemberRole addMemberRole(String email, Role role);

    Set<Role> getMemberRolesByEmail(String email);
}
