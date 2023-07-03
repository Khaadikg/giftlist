package com.peaksoft.giftlistm5.controller;

import com.peaksoft.giftlistm5.dto.*;
import com.peaksoft.giftlistm5.model.User;
import com.peaksoft.giftlistm5.repository.UserRepository;
import com.peaksoft.giftlistm5.security.jwt.JwtTokenUtil;
import com.peaksoft.giftlistm5.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    private final LoginMapper loginMapper;
    private final AuthenticationManager authenticationManager;

    @PostMapping("sign-up")
    public UserResponse signUp(@RequestBody UserRequest request) throws Exception {
        return userService.registration(request);
    }
    @PostMapping("sign-in")
    public LoginResponse signIn(@RequestBody LoginRequest loginRequest){
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword());
        authenticationManager.authenticate(token);
        User user = userRepository.findByEmail(token.getName()).get();
        return loginMapper.loginView(jwtTokenUtil.generateToken(user),"Successful",user );
    }
}
