package com.tutomato.api.gym.application;

import com.tutomato.api.gym.api.dto.GymSaveDto;
import com.tutomato.api.gym.domain.Gym;
import com.tutomato.api.gym.repository.GymRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class GymServiceImpl implements GymService {

    private final GymRepository gymRepository;

    @Override
    public Long save(GymSaveDto dto) {
        return 0L;
    }

    @Override
    public Gym findById(Long id) {
        return null;
    }

    @Override
    public List<Gym> findAll() {
        return List.of();
    }

}
