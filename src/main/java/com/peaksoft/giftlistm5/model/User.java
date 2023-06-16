package com.peaksoft.giftlistm5.model;

import com.peaksoft.giftlistm5.enums.Clothes;
import com.peaksoft.giftlistm5.enums.Gender;
import com.peaksoft.giftlistm5.enums.Role;
import com.peaksoft.giftlistm5.enums.Shoes;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", insertable=false, updatable=false)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    @CreatedDate
    @Column(name = "created_date")
    private LocalDate createdDate;
    private String password;
    @Column(name = "password_confirm")
    private String passwordConfirm;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String interests;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String city;
    @Enumerated(EnumType.STRING)
    @Column(name = "shoes_size")
    private Shoes shoesSize;
    @Enumerated(EnumType.STRING)
    @Column(name = "clothes_size")
    private Clothes clothesSize;
    private String image;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id")
    private List<User> friends;
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "user")
    private List<Charity> charities;
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "user")
    private List<Gift> gifts;
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "user")
    private List<Gift> wishes;
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "user")
    private List<Holiday> holidays;
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "user")
    private List<Complaint> complaints;

}
