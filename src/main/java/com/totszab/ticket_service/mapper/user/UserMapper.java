package com.totszab.ticket_service.mapper.user;

import com.totszab.ticket_service.dto.user.UserResponse;
import com.totszab.ticket_service.entity.user.User;

public final class UserMapper {

    private UserMapper() {
    }

    public static UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .build();
    }
}