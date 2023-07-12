package com.peaksoft.giftlistm5.controller;

import com.peaksoft.giftlistm5.dto.GiftResponse;
import com.peaksoft.giftlistm5.service.CharityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/charity")
public class CharityController {
private final CharityService charityService;

public List<GiftResponse> getAllCharityGifts(){
    return  charityService.getAllCharityGifts();
}
@DeleteMapping("{id}")
public String deleteCharityGiftById(@PathVariable Long id){
    charityService.deleteCharityGiftById(id);

return "SUCCESS";
}
