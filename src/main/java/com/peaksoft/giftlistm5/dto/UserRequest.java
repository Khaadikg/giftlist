package com.peaksoft.giftlistm5.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String passwordConfirm;
    private Boolean mailing;
}
