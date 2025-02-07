package com.tutomato.api.gym.api;

import com.tutomato.api.gym.api.dto.GymSaveDto;
import com.tutomato.api.gym.api.spec.GymApiSpec;
import com.tutomato.api.gym.application.GymService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/gym")
@RequiredArgsConstructor
public class GymController implements GymApiSpec {

    private final GymService gymService;

    @Override
    @PostMapping
    public ResponseEntity<?> save(GymSaveDto dto) {
        return ResponseEntity.ok(gymService.save(dto));
    }

    @Override
    @DeleteMapping("/{gymId}")
    public ResponseEntity<?> delete(
            @PathVariable(name = "gymId") Long gymId) {
        return null;
    }

    @Override
    @GetMapping("/gyms")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(gymService.findAll());
    }

    @Override
    @GetMapping("/{gymId}")
    public ResponseEntity<?> findById(
            @PathVariable(name = "gymId") Long gymId) {
        return ResponseEntity.ok(gymService.findById(gymId));
    }

}
