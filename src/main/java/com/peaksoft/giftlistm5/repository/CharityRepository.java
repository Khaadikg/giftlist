package com.peaksoft.giftlistm5.repository;

import com.peaksoft.giftlistm5.model.Charity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharityRepository extends JpaRepository <Charity, Long> {
}
