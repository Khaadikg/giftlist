package com.peaksoft.giftlistm5.controller;

import com.peaksoft.giftlistm5.dto.GiftRequest;
import com.peaksoft.giftlistm5.dto.GiftResponse;
import com.peaksoft.giftlistm5.service.CharityService;
import com.peaksoft.giftlistm5.service.GiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/charity")
public class CharityController {
    private final CharityService charityService;
    private final GiftService giftService;

    @GetMapping("/gifts")
    public List<GiftResponse> getPersonalCharityGifts() {
        return charityService.getPersonalCharityGifts();
    }
    @PostMapping()
    public GiftResponse create(@RequestBody GiftRequest giftRequest){
        return  giftService.create(giftRequest);
    }
    @GetMapping("{id}")
    public GiftResponse update(@RequestBody GiftRequest request, @PathVariable("id") Long id){
        return giftService.update(request, id);
    }

    @DeleteMapping("{id}")
    public String deleteCharityGiftById(@PathVariable Long id) {
        giftService.delete(id);
        return "SUCCESS";
    }

    @GetMapping("/allGifts")
    public List<GiftResponse> getAllCharityGifts() {
        return charityService.getAllCharityGifts();
    }
}

