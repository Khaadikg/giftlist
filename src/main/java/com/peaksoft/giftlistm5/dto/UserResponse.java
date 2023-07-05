package com.peaksoft.giftlistm5.dto;

import com.peaksoft.giftlistm5.enums.*;
import com.peaksoft.giftlistm5.model.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String roleName;
    private LocalDate createdDate;

}
