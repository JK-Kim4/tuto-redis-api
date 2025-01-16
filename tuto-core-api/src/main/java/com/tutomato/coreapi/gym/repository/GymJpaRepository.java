package com.tutomato.coreapi.gym.repository;

import com.tutomato.climbinggymapi.gym.domain.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymJpaRepository extends JpaRepository<Gym, Long> {

    Gym findFirstBy();
}
