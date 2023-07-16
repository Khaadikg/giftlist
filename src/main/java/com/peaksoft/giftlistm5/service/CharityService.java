package com.peaksoft.giftlistm5.service;

import com.peaksoft.giftlistm5.dto.GiftResponse;
import com.peaksoft.giftlistm5.enums.GiftType;
import com.peaksoft.giftlistm5.exception.NotFoundException;
import com.peaksoft.giftlistm5.model.Gift;
import com.peaksoft.giftlistm5.model.Notification;
import com.peaksoft.giftlistm5.model.User;
import com.peaksoft.giftlistm5.repository.GiftRepository;
import com.peaksoft.giftlistm5.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CharityService {
    private final GiftService service;
    private final UserRepository userRepository;
    private final GiftRepository giftRepository;

    public List<GiftResponse> getPersonalCharityGifts() { // все подарки с рпеозитория отмеченные isCharity = true
        User user = getAuthenticatedUser();
        return user.getGifts().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    public List<GiftResponse> getAllCharityGifts() {
        return giftRepository.findAllByIsCharity(GiftType.CHARITY).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

    }
    public GiftResponse mapToResponse(Gift gift) {
        return GiftResponse.builder()
                .id(gift.getId())
                .name(gift.getName())
                .description(gift.getDescription())
                .mainCategory(gift.getMainCategory())
                .subCategory(gift.getSubCategory())
                .state(String.valueOf(gift.getState()))
                .condition(String.valueOf(gift.getCondition()))
                .giftType(String.valueOf(gift.getGiftType()))
                .holidayId(gift.getHolidayId())
                .ownerId(gift.getOwnerId()).build();
    }
    public User getAuthenticatedUser() {
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
