package com.peaksoft.giftlistm5.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String jwt;
    private String message;
    private String authorities;
    private String firstName;
    private String lastName;
}
