package io.ecommerce.group.user_service.controller;

import io.ecommerce.group.user_service.domain.dto.request.LoginRequest;
import io.ecommerce.group.user_service.domain.dto.response.LoginResponse;
import io.ecommerce.group.user_service.service.LoginAggregation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final LoginAggregation loginAggregation;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = loginAggregation.login(request.email(), request.password(), request.stayConnected());
        return ResponseEntity.ok().body(response);
    }
}
