package com.tutomato.api.gym.application;

import com.tutomato.api.gym.api.dto.GymSaveDto;
import com.tutomato.api.gym.domain.Gym;
import com.tutomato.api.gym.repository.GymRepository;
import com.tutomato.common.exception.AlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class GymServiceImpl implements GymService {

    private final GymRepository gymRepository;

    @Override
    public Long save(GymSaveDto dto) {
        Gym gym = dto.toGym();

        if(isExistIdentifier(gym.getIdentifier())) {
            throw new AlreadyExistException(Gym.class, "이미 등록된 사업자 번호입니다.");
        }

        gymRepository.save(gym);
        return gym.getId();
    }

    @Override
    public Gym findById(Long id) {
        return gymRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Gym> findAll() {
        return gymRepository.findAll();
    }

    @Override
    public void registerMember(Long gymId, Long memberId) {

    }

    private boolean isExistIdentifier(String identifier){
        return gymRepository.existsByIdentifier(identifier);
    }

}
