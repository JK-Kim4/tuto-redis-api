package com.tutomato.api.gym.application.validator;

import com.tutomato.api.gym.application.GymService;
import com.tutomato.common.validator.ValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service @RequiredArgsConstructor
public class GymValidatorService extends ValidatorService {

    private final GymService gymService;

}
