package com.peaksoft.giftlistm5.controller;


import com.peaksoft.giftlistm5.dto.HolidayRequest;
import com.peaksoft.giftlistm5.dto.HolidayResponse;
import com.peaksoft.giftlistm5.service.HolidayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;




@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('USER')")
@Tag(name = "Holiday Auth", description = "We can creat new Holiday")
@RequestMapping("/api/holidays")
public class HolidayController {
    private final HolidayService holidayService;

    @GetMapping("all")
    @Operation(summary = "Get all holidays", description = "Only User get all Holidays")
    public List<HolidayResponse> getAll(){
        return holidayService.getAll();
    }
    @GetMapping("{id}")
    @Operation(summary = "Get by id", description = "User can get Holiday by id")
    public HolidayResponse getById(@PathVariable("id")Long id){
        return holidayService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Create holiday", description = "User can create Holiday")
    public HolidayResponse create(@RequestBody HolidayRequest holidayRequest) {
        return holidayService.create(holidayRequest);
    }

    @PutMapping("{id}")
    @Operation(summary = "update holiday", description = "User can update Holiday")
    public HolidayResponse update(@PathVariable("id") Long id, @RequestBody HolidayRequest request) {
        return holidayService.update(id, request);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "delete holiday", description = "User can delete holiday")
    public String delete(@PathVariable("id")Long id){
        holidayService.delete(id);
        return "Successfully deleted Holiday with id: "+id;
    }
}