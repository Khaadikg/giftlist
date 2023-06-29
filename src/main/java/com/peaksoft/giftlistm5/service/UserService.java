package com.peaksoft.giftlistm5.service;

import com.peaksoft.giftlistm5.dto.UserRequest;
import com.peaksoft.giftlistm5.dto.UserResponse;
import com.peaksoft.giftlistm5.model.User;
import com.peaksoft.giftlistm5.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    public UserResponse registration(UserRequest request) throws Exception{
        if (!checkValidation(request).equals("good")) {
            throw new Exception(checkValidation(request));
        }
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setMailing(request.getMailing());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return mapToResponse(user);
    }
    public UserResponse mapToResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .mailing(user.getMailing())
                .email(user.getEmail()).build();
    }
    public String checkValidation(UserRequest request) {
        if (request.getPassword().length() < 8) {
            return "password";
        }
        else if (request.getLastName() == null || request.getFirstName() == null) {
            return "name";
        }
        else if (!request.getEmail().contains("@")) {
            return "email";
        }
        return "good";
    }
}
