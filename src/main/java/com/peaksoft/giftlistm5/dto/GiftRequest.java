package com.peaksoft.giftlistm5.dto;

import com.peaksoft.giftlistm5.enums.Condition;
import com.peaksoft.giftlistm5.enums.State;
import com.peaksoft.giftlistm5.model.Complaint;
import com.peaksoft.giftlistm5.model.Holiday;
import com.peaksoft.giftlistm5.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class GiftRequest {
    private String name;
    private String description;
    private String mainCategory;
    private String subCategory;
    private String state;
    private String condition;
    private boolean isCharity;
    private boolean isWish;
    private Long holidayId;
}
