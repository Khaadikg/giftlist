package com.peaksoft.giftlistm5.service;

import com.peaksoft.giftlistm5.dto.GiftRequest;
import com.peaksoft.giftlistm5.dto.GiftResponse;
import com.peaksoft.giftlistm5.enums.Condition;
import com.peaksoft.giftlistm5.enums.State;
import com.peaksoft.giftlistm5.exception.IgnoreActionException;
import com.peaksoft.giftlistm5.exception.NotFoundException;
import com.peaksoft.giftlistm5.model.Gift;
import com.peaksoft.giftlistm5.model.User;
import com.peaksoft.giftlistm5.repository.GiftRepository;
import com.peaksoft.giftlistm5.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GiftService {
    private final GiftRepository giftRepository;
    private final UserRepository userRepository;
    public GiftResponse create(GiftRequest request) {
        User owner = getAuthenticatedUser();
        Gift gift = mapToGift(request);
        gift.setOwner(owner);
        if (request.isCharity()) {
            owner.getCharityList().add(gift);
        }
        if (request.isWish()) {
            owner.getWishes().add(gift);
        }
        return mapToResponse(giftRepository.save(gift));
    }
    public GiftResponse update(GiftRequest request, Long id) {
        Gift gift = giftRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Gift not found, by id = " + id)
        );
        if (gift.getState().equals(State.BOOKED)
            || gift.getState().equals(State.DELIVERED)
            || gift.getState().equals(State.DELIVERY)) {
            throw new IgnoreActionException("Impossible to make changes if gift is "
                    + gift.getState().toString().toLowerCase());
        }
        gift.setName(request.getName());
        gift.setDescription(request.getDescription());
        gift.setMainCategory(request.getMainCategory());
        gift.setSubCategory(request.getSubCategory());
        gift.setState(State.valueOf(request.getState()));
        gift.setCondition(Condition.valueOf(request.getCondition()));
        return mapToResponse(giftRepository.save(gift));
    }
    public String delete(Long id) {
        User user = getAuthenticatedUser();
        Gift gift = giftRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Gift not found by id = " + id)
        );
        if (gift.getState().equals(State.BOOKED)
                || gift.getState().equals(State.DELIVERED)
                || gift.getState().equals(State.DELIVERY)) {
            throw new IgnoreActionException("Impossible to make changes if gift is "
                    + gift.getState().toString().toLowerCase());
        }
        if (user.getGifts().contains(gift)) {
            user.getGifts().remove(gift);
            giftRepository.delete(gift);
            return "Gift deleted by id = " + id;
        }
        return "You dont have such gift!";
    }
    public GiftResponse getGiftById(Long id) {
        User user = getAuthenticatedUser();
        Gift gift = giftRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Gift not found by id = " + id)
        );
        if (user.getGifts().contains(gift)) {
            return mapToResponse(gift);
        }
        else throw new IgnoreActionException("You cant delete not your gifts!");
    }
    public List<GiftResponse> getAllGifts() {
        User user = getAuthenticatedUser();
        List<GiftResponse> responses = new ArrayList<>();
        for (Gift gift : user.getGifts())  {
            responses.add(mapToResponse(gift));
        }
        return responses;
    }
    public Gift mapToGift(GiftRequest request) {
        User user = getAuthenticatedUser();
        return Gift.builder()
                .name(request.getName())
                .description(request.getDescription())
                .mainCategory(request.getMainCategory())
                .subCategory(request.getSubCategory())
                .state(State.valueOf(request.getState()))
                .condition(Condition.valueOf(request.getCondition()))
                .isCharity(request.isCharity())
                .isWish(request.isWish())
                .holidayId(request.getHolidayId())
                .owner(user)
                .ownerId(user.getId()).build();
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
                .isWish(gift.isWish())
                .isCharity(gift.isCharity())
                .holidayId(gift.getHolidayId())
                .ownerId(gift.getOwnerId()).build();
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
