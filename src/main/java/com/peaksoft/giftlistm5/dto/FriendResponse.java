package com.peaksoft.giftlistm5.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FriendResponse {
    private String firstName;
    private String lastName;
    private String email;
    private Boolean mailing;
}