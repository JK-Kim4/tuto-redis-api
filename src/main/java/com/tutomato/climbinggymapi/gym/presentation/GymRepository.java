package com.tutomato.climbinggymapi.gym.presentation;

import com.tutomato.climbinggymapi.gym.domain.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymRepository extends JpaRepository<Gym, Long> {
}
