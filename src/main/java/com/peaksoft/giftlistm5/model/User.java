package com.peaksoft.giftlistm5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.peaksoft.giftlistm5.enums.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email", unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    @CreatedDate
    @Column(name = "created_date")
    private LocalDate createdDate;
    private String password;
    private int pinCode;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String interests;
    private Boolean mailing;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String city;
    @Enumerated(EnumType.STRING)
    private Country country;
    @Enumerated(EnumType.STRING)
    @Column(name = "shoes_size")
    private Shoes shoesSize;
    @Enumerated(EnumType.STRING)
    @Column(name = "clothes_size")
    private Clothes clothesSize;
    private String image;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "follower_id"))
    private Set<User> friends;
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "owner")
    @Column(name = "charity_list")
    private List<Gift> charityList; // подарки данные for charity и БЛ
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "owner")
    private List<Gift> gifts; // подарки данные на праздники и БЛ
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "owner")
    private List<Gift> wishes; // желаемые подарки на прздник
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, mappedBy = "receiver")
    private List<Gift> booking;
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "user")
    private List<Holiday> holidays;
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "owner")
    private List<Complaint> complaints;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "receiver")
    private List<Notification> receiving;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "owner")
    private List<Notification> notifications;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        return  authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
