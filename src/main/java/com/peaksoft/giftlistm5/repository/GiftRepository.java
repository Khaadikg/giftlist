package com.peaksoft.giftlistm5.repository;

import com.peaksoft.giftlistm5.model.Gift;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GiftRepository extends JpaRepository<Gift, Long> {
}
