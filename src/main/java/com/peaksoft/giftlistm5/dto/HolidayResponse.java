package com.peaksoft.giftlistm5.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class HolidayResponse {
    private Long id;
    private String name;
    private String image;
    private LocalDate date;
    private LocalDate createDate;
    private Long userId;
    private String userName;
}
