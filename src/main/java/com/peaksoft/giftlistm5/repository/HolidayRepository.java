package com.peaksoft.giftlistm5.repository;

import com.peaksoft.giftlistm5.model.Holiday;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> {

    @Query("select h from Holiday h where upper(h.name) like concat('%',:text,'%') " +
            "or upper(h.date) like concat('%',:text,'%') or upper(h.createdDate) like concat('%',:text,'%') ")
    List<Holiday> searchAndPagination(@Param("text")String text, Pageable pageable);
}
