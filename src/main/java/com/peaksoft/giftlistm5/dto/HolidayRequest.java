package com.peaksoft.giftlistm5.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
public class HolidayRequest {
    private String name;
    private String image;
    private LocalDate date;
    private LocalDate createDate;
    private Long userId;
}
