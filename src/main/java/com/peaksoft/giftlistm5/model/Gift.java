package com.peaksoft.giftlistm5.model;

import com.peaksoft.giftlistm5.enums.Category;
import com.peaksoft.giftlistm5.enums.State;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "gifts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Gift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private Category category;
    @CreatedDate
    @Column(name = "created_date")
    private LocalDate createdDate;
    @Enumerated(EnumType.STRING)
    private State state;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "holidays_id")
    private Holiday holiday;
    @Transient
    private Long holidayId;
    @OneToOne(cascade = {CascadeType.ALL})
    private User owner;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "charities_id")
    private Charity charity;
    @Transient
    private Long charityId;
}
