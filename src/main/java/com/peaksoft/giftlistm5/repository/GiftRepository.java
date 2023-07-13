package com.peaksoft.giftlistm5.repository;

import com.peaksoft.giftlistm5.model.Gift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GiftRepository extends JpaRepository<Gift, Long> {
    @Query(value = "select gift from Gift gift where gift.isCharity = true")
    List<Gift> findAllByIsCharity();

}
