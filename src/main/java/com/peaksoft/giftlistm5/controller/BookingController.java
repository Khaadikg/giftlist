package com.peaksoft.giftlistm5.controller;

import com.peaksoft.giftlistm5.model.Gift;
import com.peaksoft.giftlistm5.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Tag(name = "Booking controller", description = "Uses for charity/wish booking")
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;

    @PutMapping("/add")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Booking", description = "Uses by users to add gift to booking")
    public String addToBooking(@RequestParam Long id) {
        return bookingService.addToBooking(id);
    }
    @PutMapping("/remove")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Remove booking", description = "Uses by users to remove gift from booking")
    public String removeFromBooking(@RequestParam Long id) {
        return bookingService.removeFromBooking(id);
    }
    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "All bookings", description = "Uses by users to get all bookings")
    public Map<String, List<Gift>> getAllBookings() {
        return bookingService.getAllBookings();
    }
}
