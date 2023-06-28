package com.peaksoft.giftlistm5.controller;

import com.peaksoft.giftlistm5.dto.UserRequest;
import com.peaksoft.giftlistm5.dto.UserResponse;
import com.peaksoft.giftlistm5.model.User;
import com.peaksoft.giftlistm5.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.logging.Logger;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("sign-up")
    public UserResponse signUp(@RequestBody UserRequest request) throws Exception {
        return userService.registration(request);
    }
}
