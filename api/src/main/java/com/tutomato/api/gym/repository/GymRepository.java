package com.tutomato.api.gym.repository;

import com.tutomato.api.gym.domain.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymRepository extends JpaRepository<Gym, Long> {

    boolean existsByIdentifier(String identifier);
}
