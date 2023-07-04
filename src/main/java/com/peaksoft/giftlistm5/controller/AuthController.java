package com.peaksoft.giftlistm5.controller;

import com.peaksoft.giftlistm5.dto.UserRequest;
import com.peaksoft.giftlistm5.dto.UserResponse;
import com.peaksoft.giftlistm5.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("sign-up")
    public UserResponse signUp(@RequestBody UserRequest request) {
        return userService.registration(request);
    }
}
