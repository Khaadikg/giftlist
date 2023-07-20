package com.peaksoft.giftlistm5.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class CharityResponse {
    private Long id;
    private String name;
    private LocalDate createdDate;
    private String image;
    private String mainCategory;
    private String subCategory;
    private Long ownerId;
}
