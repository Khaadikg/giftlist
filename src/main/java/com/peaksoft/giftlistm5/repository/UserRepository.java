package com.peaksoft.giftlistm5.repository;

import com.peaksoft.giftlistm5.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository   extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    @Query("select u from User u where u.id = :userId")
    Optional<List<User>> findAllById(@Param("userId") Long id);
}
