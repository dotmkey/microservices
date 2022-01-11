package com.dotmkey.microservices.auth.port.http.controller.registration.email;

import com.dotmkey.microservices.auth.application.email.usecase.AttemptToRegister;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController("1")
@AllArgsConstructor
public class Controller {
    private final AttemptToRegister useCase;

    @PostMapping("/registration/attempt")
    public void attemptToRegister(@Valid @RequestBody Request request) {
        this.useCase.execute(request.email());
    }
}
