package com.peaksoft.giftlistm5.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
public class HolidayRequest {
    private String name;
    private String image;
    private LocalDate date;
    private LocalDate createDate;
    private Long userId;
}
