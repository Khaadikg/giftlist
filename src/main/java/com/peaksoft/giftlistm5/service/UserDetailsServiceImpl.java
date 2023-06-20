package com.peaksoft.giftlistm5.service;

import com.peaksoft.giftlistm5.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    @Autowired
    private  UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email ) throws UsernameNotFoundException {


        return userRepository.finByEmail(email)
                .orElseThrow(
                        ()-> new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG,email)));

    }
}
