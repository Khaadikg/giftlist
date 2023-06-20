package com.peaksoft.giftlistm5.repository;

import com.peaksoft.giftlistm5.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
public interface UserRepository   extends JpaRepository<User, Long> {
    @Query("select u from User u where u.email =:email")

    User getUserByUserEmail(@Param("email") String email);
    Optional<User> finByEmail(String email);
}
