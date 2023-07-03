package com.peaksoft.giftlistm5.dto;

import com.peaksoft.giftlistm5.enums.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Getter
@Setter
public class LoginResponse {
    private String jwt;
    private String message;
    private String authorities;
    private String firstName;
    private String lastName;

}
