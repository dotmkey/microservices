package com.dotmkey.microservices.auth.port.http.controller.registration.email;

import javax.validation.constraints.Email;

public record Request(@Email String email) {
}
