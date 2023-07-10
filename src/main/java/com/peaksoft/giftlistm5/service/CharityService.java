package com.peaksoft.giftlistm5.service;

import com.peaksoft.giftlistm5.dto.GiftResponse;
import com.peaksoft.giftlistm5.exception.NotFoundException;
import com.peaksoft.giftlistm5.model.Notification;
import com.peaksoft.giftlistm5.model.User;
import com.peaksoft.giftlistm5.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CharityService {
    private final GiftService service;
    private final UserRepository userRepository;
    public List<GiftResponse> getAllCharityGifts() { // все подарки с рпеозитория отмеченные isCharity = true
        return null;
    }
    public String deleteCharityGiftById(Long id) {
        return"SUCCESS";
    }
    public Notification giftStatusActions(Long id, String status) {
        return null;
    }
    public User getAuthenticatedUser(){
        Authentication auth;
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            throw new NotFoundException("Authenticated user is null");
        } else {
            auth = SecurityContextHolder.getContext().getAuthentication();
        }
        return userRepository.findByEmail(auth.getName()).orElseThrow(
                () -> new NotFoundException("User not found by email = " + auth.getName())
        );
    }

}
