package com.peaksoft.giftlistm5.repository;

import com.peaksoft.giftlistm5.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
