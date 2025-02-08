package com.tutomato.api.gym.application;

import com.tutomato.api.gym.api.dto.GymSaveDto;
import com.tutomato.api.gym.domain.Gym;
import com.tutomato.api.member.domain.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GymService {

    Long save(GymSaveDto dto);

    Gym findById(Long id);

    List<Gym> findAll();

    void registerMember(Long gymId, Long memberId);
}
