package com.peaksoft.giftlistm5.dto;

import com.peaksoft.giftlistm5.model.Gift;
import com.peaksoft.giftlistm5.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CharityRequest {

//    private String name;
//    private String image;
//    private String mainCategory;

private Long id;

    private String name;

    private LocalDate createdDate;
    private String image;

    private String mainCategory; // главная категория подарка для БЛ выбирается авто с категории Бл
    //    private String subCategory; суб категория выбирается уже при подачи подарка

    private User owner;

    private Long ownerId;
    private List<Gift> balance; // подарки на пожертвования


}
