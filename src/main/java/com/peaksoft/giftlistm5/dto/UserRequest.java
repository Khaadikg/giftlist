package com.peaksoft.giftlistm5.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserRequest {
    @NotEmpty(message = "Please enter valid name.")
    private String firstName;
    @NotEmpty(message = "Please enter last name.")
    private String lastName;
    private String email;
    private String password;
    private String passwordConfirm;
    private Boolean mailing;
}
