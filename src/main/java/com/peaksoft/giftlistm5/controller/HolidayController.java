package com.peaksoft.giftlistm5.controller;


import com.peaksoft.giftlistm5.dto.HolidayRequest;
import com.peaksoft.giftlistm5.dto.HolidayResponse;
import com.peaksoft.giftlistm5.service.HolidayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/holidays")
public class HolidayController {
    private final HolidayService holidayService;

    @GetMapping("all")
   // @Operation(summary = "Get all holidays", description = "Only Admin get all Holidays")
    public List<HolidayResponse> getAll(){
        return holidayService.getAll();
    }
    @GetMapping("{id}")
//    @Operation(summary = "Get by id", description = "Admin can get Holiday by id")
    public HolidayResponse getById(@PathVariable("id")Long id){
        return holidayService.getById(id);
    }

    @PostMapping
    public HolidayResponse create(@RequestBody HolidayRequest holidayRequest) {
        return holidayService.create(holidayRequest);
    }

    @PutMapping("{id}")
    public HolidayResponse update(@PathVariable("id") Long id, @RequestBody HolidayRequest request) {
        return holidayService.update(id, request);
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id")Long id){
        holidayService.delete(id);
        return "Successfully deleted Holiday with id: "+id;
    }
}