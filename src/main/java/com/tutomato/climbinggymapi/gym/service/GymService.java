package com.tutomato.climbinggymapi.gym.service;

import com.tutomato.climbinggymapi.gym.domain.Gym;
import com.tutomato.climbinggymapi.gym.domain.Gyms;
import com.tutomato.climbinggymapi.gym.domain.dto.GymSaveDto;
import com.tutomato.climbinggymapi.gym.repository.GymRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GymService {

    private final GymRepository gymRepository;

    @Transactional
    public Long save(GymSaveDto dto){
        Gym gym = gymRepository.save(new Gym(dto));
        return gym.getId();
    }
    @Transactional
    public void deleteAll(){
        gymRepository.deleteAll();
    }

    public Gym findGymById(Long gymId){
        return gymRepository.findById(gymId).orElse(null);
    }

    public List<Gym> findAllGyms(){
        return gymRepository.findAll();
    }

    @Cacheable(key = "#gymId", value = "Gym")
    public Gym findGymByIdWhitCache(Long gymId){
        return gymRepository.findById(gymId).orElseThrow(() -> new RuntimeException("Gym not found"));
    }

    @Cacheable(cacheNames = "gyms", key = "'all'")
    public Gyms findAllGymsWithCache(){
        List<Gym> gyms = gymRepository.findAll();
        return new Gyms(gyms);
    }

}
