package com.tutomato.api.member.application;

import com.tutomato.api.member.api.dto.MemberSaveDto;
import com.tutomato.api.member.domain.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MemberService {

    Long save(MemberSaveDto dto);

    Member findById(Long memberId);

    List<Member> findAll();

}
