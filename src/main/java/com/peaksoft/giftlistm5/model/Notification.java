package com.peaksoft.giftlistm5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.peaksoft.giftlistm5.enums.NotificationType;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreatedDate
    @Column(name = "created_date")
    private LocalDate createdDate;
    private String message;
    @Enumerated(EnumType.STRING)
    private NotificationType type;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.MERGE,CascadeType.REMOVE})
    @JoinColumn(name = "receivers_id")
    @JsonIgnore
    private User receiver;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.MERGE,CascadeType.REMOVE})
    @JoinColumn(name = "users_id")
    @JsonIgnore
    private User owner;
}
