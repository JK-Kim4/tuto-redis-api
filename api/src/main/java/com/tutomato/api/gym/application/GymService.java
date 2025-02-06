package com.tutomato.api.gym.application;

import com.tutomato.api.gym.domain.Gym;

import java.util.List;

public interface GymService {

    Long save(Gym gym);

    Gym findById(Long id);

    List<Gym> findAll();

}
