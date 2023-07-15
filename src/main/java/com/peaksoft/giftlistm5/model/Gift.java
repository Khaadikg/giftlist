package com.peaksoft.giftlistm5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.peaksoft.giftlistm5.enums.Condition;
import com.peaksoft.giftlistm5.enums.GiftType;
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
    @Column(name = "gift_type")
    @Enumerated(EnumType.STRING)
    private GiftType giftType;
//    @Transient
//    @Column(name = "gift_type_string")
//    private String giftTypeString;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "holidays_id")
    private Holiday holiday;
    @Transient
    private Long holidayId;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinColumn(name = "users_id")
    @JsonIgnore
    private User owner;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "receiver_id")
    @JsonIgnore
    private User receiver;
    @Transient
    private Long ownerId;
//    {
//        mapToString();
//    }
//    private void mapToString() {
//        this.giftTypeString = String.valueOf(this.giftType);
//    }
}
