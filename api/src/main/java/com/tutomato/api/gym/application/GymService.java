package com.tutomato.api.gym.application;

import com.tutomato.api.gym.api.dto.GymSaveDto;
import com.tutomato.api.gym.domain.Gym;

import java.util.List;

public interface GymService {

    Long save(GymSaveDto dto);

    Gym findById(Long id);

    List<Gym> findAll();

}
