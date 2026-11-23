package com.university.iam.service;

import com.university.iam.model.Role;
import com.university.iam.model.User;
import com.university.iam.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(String fullName, String email, String password) {

        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException(
                    "This email is already registered"
            );
        }

        User user = new User();

        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(Role.STUDENT);
        user.setEnabled(true);

        return userRepository.save(user);
    }
}
