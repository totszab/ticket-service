package com.totszab.ticket_service.service.user;

import com.totszab.ticket_service.config.JwtService;
import com.totszab.ticket_service.dto.user.LoginRequest;
import com.totszab.ticket_service.dto.user.RegisterRequest;
import com.totszab.ticket_service.entity.user.User;
import com.totszab.ticket_service.exception.user.EmailAlreadyExistsException;
import com.totszab.ticket_service.exception.user.InvalidCredentialsException;
import com.totszab.ticket_service.exception.user.UserNotFoundException;
import com.totszab.ticket_service.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public User register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));

        return userRepository.save(user);
    }

    public String login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(InvalidCredentialsException::new);

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new InvalidCredentialsException();
        }

        return jwtService.generateToken(user.getEmail(), user.getRole());
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}