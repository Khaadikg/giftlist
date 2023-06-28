package com.peaksoft.giftlistm5.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserRequest {
    private OAuth2AuthenticationToken oAuth2AuthenticationToken;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String passwordConfirm;
    private Boolean mailing;
}
