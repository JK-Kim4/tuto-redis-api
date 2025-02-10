package com.tutomato.api.gym.api.spec;

import com.tutomato.api.gym.api.dto.GymSaveDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Table;
import org.springframework.http.ResponseEntity;

@Tag(name = "Gym API")
public interface GymApiSpec {

    ResponseEntity<?> save(GymSaveDto dto);

    ResponseEntity<?> delete(Long id);

    ResponseEntity<?> findAll();

    ResponseEntity<?> findById(Long id);

}
