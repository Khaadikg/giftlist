package com.peaksoft.giftlistm5.controller;

import com.peaksoft.giftlistm5.dto.UserResponse;
import com.peaksoft.giftlistm5.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth")
@AllArgsConstructor
public class SignupWithGoogle {
    private final UserService userService;

    @GetMapping("/signUpWithGoogle")
    public UserResponse signUpWithGoogle(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        return userService.createAndSaveUserByGmail(oAuth2AuthenticationToken);
    }

}
