package com.tutomato.coreapi.gym.service;

import com.tutomato.climbinggymapi.common.exception.GymApiCustomException;
import com.tutomato.climbinggymapi.gym.domain.Gym;
import com.tutomato.climbinggymapi.gym.domain.Gyms;
import com.tutomato.climbinggymapi.gym.domain.dto.GymSaveDto;
import com.tutomato.climbinggymapi.gym.repository.GymJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GymService {

    private final GymJpaRepository gymJpaRepository;

    @Transactional
    public Long save(GymSaveDto dto){
        Gym gym = gymJpaRepository.save(new Gym(dto));
        return gym.getId();
    }
    @Transactional
    public void deleteAll(){
        gymJpaRepository.deleteAll();
    }

    @Transactional
    @CacheEvict(value = "Gym", key = "#dto.id")
    public Long update(GymSaveDto dto){
        Gym gym = gymJpaRepository.findById(dto.getId())
                .orElseThrow(() -> new GymApiCustomException("체육관 조회에 실패하였습니다. 체육관 고유 변호를 확인 후 다시 시도해주세요."));

        gym.updateGymInformation(dto);
        return gym.getId();
    }

    public Gym findGymById(Long gymId){
        return gymJpaRepository.findById(gymId).orElse(null);
    }

    public List<Gym> findAllGyms(){
        return gymJpaRepository.findAll();
    }

    @Cacheable(key = "#gymId", value = "Gym")
    public Gym findGymByIdWhitCache(Long gymId){
        return gymJpaRepository.findById(gymId).orElseThrow(() -> new RuntimeException("Gym not found"));
    }

    @Cacheable(cacheNames = "gyms", key = "'all'")
    public Gyms findAllGymsWithCache(){
        List<Gym> gyms = gymJpaRepository.findAll();
        return new Gyms(gyms);
    }

}
