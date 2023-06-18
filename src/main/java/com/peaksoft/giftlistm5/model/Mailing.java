package com.peaksoft.giftlistm5.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "mailings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mailing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String theme;
    private String description;
    @CreatedDate
    @Column(name = "created_date")
    private LocalDate createdDate;
}
