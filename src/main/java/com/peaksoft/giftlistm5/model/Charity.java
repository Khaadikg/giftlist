package com.peaksoft.giftlistm5.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "charities")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Charity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @CreatedDate
    @Column(name = "created_date")
    private LocalDate createdDate;
    private String image;
    @Column(name = "main_category")
    private String mainCategory; // главная категория подарка для БЛ выбирается авто с категории Бл
    private String subCategory; //суб категория выбирается уже при подачи подарка
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH,CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "users_id")
    private User owner;
    @Transient
    private Long ownerId;
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "charity")
    private List<Gift> balance; // подарки на пожертвования

}
