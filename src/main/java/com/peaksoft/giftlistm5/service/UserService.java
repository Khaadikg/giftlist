package com.peaksoft.giftlistm5.service;

import com.peaksoft.giftlistm5.dto.*;
import com.peaksoft.giftlistm5.enums.Role;
import com.peaksoft.giftlistm5.exception.IncorrectLoginException;
import com.peaksoft.giftlistm5.model.User;
import com.peaksoft.giftlistm5.repository.UserRepository;
import com.peaksoft.giftlistm5.security.jwt.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final LoginMapper loginMapper;

    public UserResponse registration(UserRequest request) {
        if (!checkValidation(request).equals("good")) {
            throw new IncorrectLoginException(checkValidation(request));
        }
        User user = new User();
        user.setFirstName(request.getFirstName());
        if (request.getLastName().isEmpty() || request.getFirstName().isEmpty()) {
            throw new IncorrectLoginException("First name and last name must not be empty");
        }
        user.setLastName(request.getLastName());
        Optional<User> user1 = Optional.ofNullable(userRepository.findByEmail(request.getEmail())).get();
        if (user1.isPresent()) {
            throw new IncorrectLoginException("User with this mail already exists in the database");
        }
        user.setEmail(request.getEmail());
        user.setRole(Role.USER);
        user.setMailing(request.getMailing());
        if (request.getPassword() == null) {
            user.setPassword(passwordEncoder.encode(request.getFirstName()));
        } else if (request.getPassword().equals(request.getPasswordConfirm())) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        } else {
            throw new IncorrectLoginException("Passwords do not match");
        }
        userRepository.save(user);
        return mapToResponse(user);
    }

    public UserResponse mapToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .roleName(user.getRole().getAuthority())
                .createdDate(LocalDate.now())
                .build();
    }

    public String checkValidation(UserRequest request) {
        if (request.getPassword().length() < 8) {
            return "The password must contain one capital letter and one character";
        } else if (request.getLastName() == null || request.getFirstName() == null) {
            return "First name and last name must not be empty";
        } else if (!request.getEmail().contains("@")) {
            return "Email is not correct";
        }
        return "good";
    }

    public LoginResponse login(LoginRequest loginRequest) {
        User existUser = userRepository.findByEmail(loginRequest.getEmail())
                .stream().findAny().orElseThrow(
                        () -> new IncorrectLoginException("Email is not correct"));
        if (passwordEncoder.matches(loginRequest.getPassword(), existUser.getPassword())) {
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
            User user = userRepository.findByEmail(token.getName()).get();
            return loginMapper.loginView(jwtTokenUtil.generateToken(user), "SUCCESS", user);
        } else {
            throw new IncorrectLoginException("Password is not correct" + " or " + "Access denied! You are not registered");
        }
    }
}
