package com.peaksoft.giftlistm5.repository;

import com.peaksoft.giftlistm5.enums.GiftType;
import com.peaksoft.giftlistm5.model.Gift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface GiftRepository extends JpaRepository<Gift, Long> {
    @Query(value = "select gift from Gift gift where gift.giftType = :key")
    List<Gift> findAllByIsCharity(@Param("key") GiftType giftType);

}
