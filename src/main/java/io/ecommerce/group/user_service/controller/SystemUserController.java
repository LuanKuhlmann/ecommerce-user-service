package io.ecommerce.group.user_service.controller;

import io.ecommerce.group.user_service.domain.dto.request.RegisterAddressRequest;
import io.ecommerce.group.user_service.domain.dto.request.RegisterUserRequest;
import io.ecommerce.group.user_service.domain.dto.response.RegisteredAddressResponse;
import io.ecommerce.group.user_service.domain.dto.response.RegisteredUserResponse;
import io.ecommerce.group.user_service.service.SystemUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class SystemUserController {

    private final SystemUserService service;

    @PostMapping("/register")
    public ResponseEntity<RegisteredUserResponse> registerNewUser(@RequestBody @Valid RegisterUserRequest request) {
        RegisteredUserResponse response = service.registerNewUser(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register/address/{userId}")
    public ResponseEntity<RegisteredAddressResponse> registerNewAddress(
            @PathVariable Long userId,
            @RequestBody @Valid RegisterAddressRequest request) {
        RegisteredAddressResponse response = service;
    }
}
