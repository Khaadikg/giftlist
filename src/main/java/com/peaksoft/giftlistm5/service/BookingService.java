package com.peaksoft.giftlistm5.service;

import com.peaksoft.giftlistm5.enums.State;
import com.peaksoft.giftlistm5.exception.NotFoundException;
import com.peaksoft.giftlistm5.model.Gift;
import com.peaksoft.giftlistm5.model.User;
import com.peaksoft.giftlistm5.repository.GiftRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingService {
    private final GiftRepository giftRepository;

    public String addToBooking(Long id) {
        User user = getAuthorisedUser();
        Gift gift = giftRepository.findById(id).orElseThrow(
                () -> new NotFoundException("There is no gift by id = " + id)
        );
        if (gift.getState().equals(State.BOOKED)) {
            throw new NotFoundException("This gift is already booked!");
        }
        gift.setReceiver(user);
        gift.setState(State.BOOKED);
        user.getBooking().add(gift);
        giftRepository.save(gift);
        return "Gift " + gift.getName() + " added to booking!";
    }
    public String removeFromBooking(Long id) {
        User user = getAuthorisedUser();
        Gift gift = giftRepository.findById(id).orElseThrow(
                () -> new NotFoundException("There is no gift by id = " + id)
        );
        gift.setReceiver(null);
        gift.setState(State.UNBOOKED);
        user.getBooking().remove(gift);
        giftRepository.save(gift);
        return "Gift " + gift.getName() + " removed from booking!";
    }
    public Map<String, List<Gift>> getAllBookings() {
        User user = getAuthorisedUser();
        List<Gift> wishGifts = new ArrayList<>();
        List<Gift> charityGifts = new ArrayList<>();
        for (Gift gift : user.getBooking()) {
            if (gift.getState().equals(State.BOOKED) && gift.isCharity()) {
                charityGifts.add(gift);
            }
            else wishGifts.add(gift);
        }
        Map<String, List<Gift>> map = new HashMap<>();
        map.put("wish", wishGifts);
        map.put("charity", charityGifts);
        return map;
    }
    public User getAuthorisedUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
