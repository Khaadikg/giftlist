package com.peaksoft.giftlistm5.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class CharityResponse {
    private Long id;
    private String name;
    private String image;
    private String mainCategory;
    private LocalDate createdDate;

}
