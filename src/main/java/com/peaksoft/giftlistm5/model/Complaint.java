package com.peaksoft.giftlistm5.model;

import com.peaksoft.giftlistm5.enums.ComplaintType;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "complaints")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // короткое описание жалобы
    private String headline;
    // развернутое описание жалобы
    private String description;
    @Enumerated(EnumType.STRING)
    private ComplaintType type;
    // кто написал жалобу -> owner
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.MERGE,CascadeType.REMOVE}, mappedBy = "complaint")
    private User owner;
    // какому подарку принадлежит жалоба
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.MERGE,CascadeType.REMOVE}, mappedBy = "complaint")
    private Gift gift;

}
