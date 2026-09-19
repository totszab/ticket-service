package com.totszab.ticket_service.controller.user;

import com.totszab.ticket_service.dto.user.UserResponse;
import com.totszab.ticket_service.mapper.user.UserMapper;
import com.totszab.ticket_service.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable Long id) {
        return UserMapper.toResponse(userService.findById(id));
    }
}