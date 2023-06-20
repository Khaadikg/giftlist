package com.peaksoft.giftlistm5.request;



import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class
ForgotPasswordRequest {
    private Long id;
    @NotNull
    @NotBlank
    private Spring newPassword;
    @NotNull
    @NotBlank
//    @PasswordValid
    private String verifyPassword;
}
