package com.peaksoft.giftlistm5.controller;

import com.peaksoft.giftlistm5.dto.UserRequest;
import com.peaksoft.giftlistm5.dto.UserResponse;
import com.peaksoft.giftlistm5.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("sign-up")
    public UserResponse signUp(@RequestBody UserRequest request) throws Exception {
        return userService.registration(request);
    }
    @GetMapping("/oauth2")
    public UserResponse create(OAuth2AuthenticationToken oAuth2AuthenticationToken) throws Exception {
        return userService.createAndSaveUserByGmail(oAuth2AuthenticationToken);
    }
}
