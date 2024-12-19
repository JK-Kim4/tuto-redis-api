package com.tutomato.climbinggymapi.gym.repository;

import com.tutomato.climbinggymapi.gym.domain.Gym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GymRepository extends JpaRepository<Gym, Long> {

    Gym findFirstBy();
}
