package com.peaksoft.giftlistm5.dto;

import com.peaksoft.giftlistm5.enums.Condition;
import com.peaksoft.giftlistm5.enums.State;
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
    private String state;//Enum
    private String condition;//Enum
    private boolean isCharity; // true if it is for charity
    private boolean isWish;
    private Long holidayId;// null if it is for charity
    private Long ownerId;
}


