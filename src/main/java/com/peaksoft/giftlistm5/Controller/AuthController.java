package com.peaksoft.giftlistm5.Controller;

import com.peaksoft.giftlistm5.dto.*;
import com.peaksoft.giftlistm5.model.User;
import com.peaksoft.giftlistm5.repository.UserRepository;
import com.peaksoft.giftlistm5.security.jwt.JwtTokenFilter;
import com.peaksoft.giftlistm5.security.jwt.JwtTokenUtil;
import com.peaksoft.giftlistm5.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jwt")
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    private final LoginMapper loginMapper;
    private final AuthenticationManager authenticationManager;

    @PostMapping("sing-up")
    public UserResponse signIn(@RequestBody UserRequest userRequest){
        return userService.create(userRequest);
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
