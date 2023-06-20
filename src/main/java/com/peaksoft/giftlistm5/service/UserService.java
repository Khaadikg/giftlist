package com.peaksoft.giftlistm5.service;

import com.peaksoft.giftlistm5.dto.UserRequest;
import com.peaksoft.giftlistm5.dto.UserResponse;
import com.peaksoft.giftlistm5.enums.Role;
import com.peaksoft.giftlistm5.model.User;
import com.peaksoft.giftlistm5.repository.UserRepository;
import com.peaksoft.giftlistm5.security.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse create(UserRequest request){
        User user = new User();
        user.setLastName(request.getLastName());
        user.setFirstName(request.getFirstName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(request.getPassword()));
        user.setRole(Role.valueOf((request.getRoleName())));
        user.setCreatedDate(LocalDate.now());
        userRepository.save(user);
        return mapToResponse(user);
    }
    public UserResponse mapToResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .lastName(user.getLastName())
                .firstName(user.getFirstName())
                .email(user.getEmail())
                .roleName(user.getRole().name())
                .createdDate(user.getCreatedDate()).build();
    }
    public List<UserResponse> getAll(){
        List<UserResponse> userResponses = new ArrayList<>();
        for(User user: userRepository.findAll()){
            userResponses.add(mapToResponse(user));
        }
        return userResponses;
    }


}
