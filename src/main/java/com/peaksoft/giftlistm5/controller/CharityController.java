package com.peaksoft.giftlistm5.controller;

import com.peaksoft.giftlistm5.dto.GiftRequest;
import com.peaksoft.giftlistm5.dto.GiftResponse;
import com.peaksoft.giftlistm5.service.CharityService;
import com.peaksoft.giftlistm5.service.GiftService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/charity")
@Tag(name = "Charity controller",description = "Controller for managing charity operations")
public class CharityController {
    private final CharityService charityService;
    private final GiftService giftService;

    @GetMapping("/gifts")
    @Operation(summary = "Get personal charity gifts", description = "List of personal charity gifts")
    public List<GiftResponse> getPersonalCharityGifts() {
        return charityService.getPersonalCharityGifts();
    }

    @PostMapping()
    @Operation(summary = "Create", description = "Create a new gift")
    public GiftResponse create(@RequestBody GiftRequest giftRequest) {
        return giftService.create(giftRequest);
    }

    @GetMapping("{id}")
    @Operation(summary = "Update", description = "Update a charity gift by its ID")
    public GiftResponse update(@RequestBody GiftRequest request, @PathVariable("id") Long id) {
        return giftService.update(request, id);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete charity gift", description = "Delete a charity gift by its ID")
    public String deleteCharityGiftById(@PathVariable Long id) {
        giftService.delete(id);
        return "SUCCESS";
    }

    @GetMapping("/allGifts")
    @Operation(summary = "Get charity gifts", description = "List of all charity gifts")
    public List<GiftResponse> getAllCharityGifts() {
        return charityService.getAllCharityGifts();
    }
}

