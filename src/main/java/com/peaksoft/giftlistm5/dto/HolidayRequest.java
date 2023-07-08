package com.peaksoft.giftlistm5.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class HolidayRequest {
    private String name;
    private String image;
    private LocalDate date;
}
