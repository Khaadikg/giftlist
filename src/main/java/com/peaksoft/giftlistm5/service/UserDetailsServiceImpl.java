package com.peaksoft.giftlistm5.service;

import com.peaksoft.giftlistm5.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    @Autowired
    private  UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username ) throws UsernameNotFoundException {


        return userRepository.findByEmail(username)
                .orElseThrow(
                        ()-> new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG,username)));

    }
}
