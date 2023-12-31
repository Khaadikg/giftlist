package com.peaksoft.giftlistm5.controller;

import com.peaksoft.giftlistm5.dto.LoginRequest;
import com.peaksoft.giftlistm5.dto.LoginResponse;
import com.peaksoft.giftlistm5.dto.UserRequest;
import com.peaksoft.giftlistm5.dto.UserResponse;
import com.peaksoft.giftlistm5.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("sign-up")
    public UserResponse signUp(@RequestBody UserRequest request) {
        return userService.registration(request);
    }

    @PostMapping("sign-in")
    public LoginResponse signIn(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }
}
