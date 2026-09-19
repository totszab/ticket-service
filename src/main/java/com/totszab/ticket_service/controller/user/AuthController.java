package com.totszab.ticket_service.controller.user;

import com.totszab.ticket_service.dto.user.RegisterRequest;
import com.totszab.ticket_service.dto.user.UserResponse;
import com.totszab.ticket_service.entity.user.User;
import com.totszab.ticket_service.mapper.user.UserMapper;
import com.totszab.ticket_service.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request) {
        User created = userService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toResponse(created));
    }
}