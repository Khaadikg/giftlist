package com.peaksoft.giftlistm5.controller;

import com.peaksoft.giftlistm5.dto.GiftRequest;
import com.peaksoft.giftlistm5.dto.GiftResponse;
import com.peaksoft.giftlistm5.service.GiftService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.build.Plugin;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gifts")
@PreAuthorize("hasAuthority('USER')")
@Tag(name = "Gifts", description = "Operations related to gifts")
public class GiftController {
    private final GiftService giftService;

    @PostMapping()
    @Operation(summary = "Create", description = "Create a new gift")
    public GiftResponse create(@RequestBody GiftRequest giftRequest) {
        return giftService.create(giftRequest);
    }

    @PutMapping("{id}")
    @Operation(summary = "Update", description = "Update a gift by its ID")
    public GiftResponse update(@RequestBody GiftRequest request, @PathVariable("id") Long id) {
        return giftService.update(request, id);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete", description = "Delete a gift by its ID")
    public String delete(@PathVariable("id") Long id) {
        giftService.delete(id);
        return "Successfully gift  deleted with id : " + id;
    }

    @GetMapping("{id}")
    @Operation(summary = "Get by id", description = "Retrieve a gift by its ID")
    public GiftResponse getById(@PathVariable("id") Long id) {
        return giftService.getGiftById(id);
    }

    @GetMapping()
    @Operation(summary = "Get all gifts", description = "Retrieve a list of all gifts")
    public List<GiftResponse> getAll() {
        return giftService.getAllGifts();
    }

}
