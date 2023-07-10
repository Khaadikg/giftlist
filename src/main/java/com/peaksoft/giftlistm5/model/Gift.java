package com.peaksoft.giftlistm5.model;

import com.peaksoft.giftlistm5.enums.Condition;
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
    @Column(name = "main_category")
    private String mainCategory;
    @Column(name = "sub_category")
    private String subCategory;
    @CreatedDate
    @Column(name = "created_date")
    private LocalDate createdDate;
    @Enumerated(EnumType.STRING)
    private State state;
    @Enumerated(EnumType.STRING)
    private Condition condition;
    @OneToOne(cascade = CascadeType.ALL)
    private Complaint complaint;
    private boolean isCharity;
    private boolean isWish;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "holidays_id")
    private Holiday holiday;
    @Transient
    private Long holidayId;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.PERSIST})
    @JoinColumn(name = "users_id")
    private User owner;
    @Transient
    private Long ownerId;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "charities_id")
    private Charity charity;
    @Transient
    private Long charityId;
}
