package com.peaksoft.giftlistm5.repository;

import com.peaksoft.giftlistm5.model.Gift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GiftRepository extends JpaRepository<Gift, Long> {
    List<Gift> findAllByIsCharity(boolean isCharity);
}
