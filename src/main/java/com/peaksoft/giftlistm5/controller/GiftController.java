package com.peaksoft.giftlistm5.controller;

import com.peaksoft.giftlistm5.dto.GiftRequest;
import com.peaksoft.giftlistm5.dto.GiftResponse;
import com.peaksoft.giftlistm5.model.Gift;
import com.peaksoft.giftlistm5.service.GiftService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gifts")
public class GiftController {
    private final GiftService giftService;
    @PostMapping()
    @PreAuthorize("hasAuthority('USER')")
    public GiftResponse create(@RequestBody GiftRequest giftRequest){
      return  giftService.create(giftRequest);
    }
    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('USER')")
    public GiftResponse update(@RequestBody GiftRequest request, @PathVariable("id") Long id){
        return giftService.update(request, id);
    }
    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('USER')")
    public String delete(@PathVariable("id") Long id) {
        giftService.delete(id);
        return "Successfully gift  deleted with id : " + id;
    }
    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('USER')")
    public GiftResponse getById(@PathVariable("id") Long id) {
        return giftService.getGiftById(id);
    }
    @GetMapping()
    public List<GiftResponse> getAll() {
        return giftService.getAllGifts();
    }
}
