package com.peaksoft.giftlistm5.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GiftResponse {
    private Long id;
    private String name;
    private String description;
    private String mainCategory;
    private String subCategory;
    private String state;
    private String condition;
    private String giftType;
    private Long holidayId;//
    private Long ownerId;
}


