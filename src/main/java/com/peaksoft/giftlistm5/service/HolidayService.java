package com.peaksoft.giftlistm5.service;

import com.peaksoft.giftlistm5.dto.HolidayRequest;
import com.peaksoft.giftlistm5.dto.HolidayResponse;
import com.peaksoft.giftlistm5.dto.HolidayResponseView;
import com.peaksoft.giftlistm5.exception.NotFoundException;
import com.peaksoft.giftlistm5.model.Holiday;
import com.peaksoft.giftlistm5.model.User;
import com.peaksoft.giftlistm5.repository.HolidayRepository;
import com.peaksoft.giftlistm5.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class HolidayService {
    private final HolidayRepository holidayRepository;
    public List<HolidayResponse> getAll() {
        List<HolidayResponse> holidayResponses = new ArrayList<>();
        for (Holiday holiday : holidayRepository.findAll()) {
            holidayResponses.add(mapToResponse(holiday));
        }
        return holidayResponses;
    }

    public HolidayResponse getById(Long id) {
        Holiday holiday = holidayRepository.findById(id).get();
        return mapToResponse(holiday);
    }

    public HolidayResponse create(HolidayRequest request) {
        User user = getAuthUser();
        Holiday holiday = new Holiday();
        holiday.setName(request.getName());
        holiday.setImage(request.getImage());
        if (request.getImage() == null){
            holiday.setImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQQIiwDDS9S7wYp5muIVCt9PwRmmQpP5rSYkg&usqp=CAU");
        }else {
            holiday.setImage(request.getImage());
        }
        holiday.setDate(request.getDate());
        holiday.setCreatedDate(LocalDate.now());
        holiday.setUser(user);
        holidayRepository.save(holiday);
        log.info("create holiday ok");
        return mapToResponse(holiday);
    }

    public HolidayResponse update(Long id, HolidayRequest request) {
        Holiday holiday = holidayRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Holiday not found by id = " + id)
        );
        holiday.setName(request.getName());
        holiday.setImage(request.getImage());
        holiday.setDate(request.getDate());
        holidayRepository.save(holiday);
        return mapToResponse(holiday);
        }

    public void delete(Long id) {
        holidayRepository.deleteById(id);
//        Holiday holiday = holidayRepository.findById(id).get();
//        holidayRepository.deleteById(holiday.getId());
//        lo
    }

    public HolidayResponse mapToResponse(Holiday holiday) {
        User user = getAuthUser();
        return HolidayResponse.builder()
                .id(holiday.getId())
                .name(holiday.getName())
                .image(holiday.getImage())
                .date(holiday.getDate())
                .createDate(LocalDate.now())
                .ownerName(user.getFirstName() + " " + user.getLastName())
                .build();
    }

    public HolidayResponseView searchAndPagination(String text, int page, int size){
        Pageable pageable = PageRequest.of(page-1, size);
        HolidayResponseView responseView = new HolidayResponseView();
        responseView.setHolidayResponses(view(search(text,pageable)));
        return responseView;
    }

    public List<HolidayResponse> view(List<Holiday> holidays){
        List<HolidayResponse> holidayResponses = new ArrayList<>();
        for (Holiday holiday : holidays) {
            holidayResponses.add(mapToResponse(holiday));
        }
        return holidayResponses;
    }

    private List<Holiday> search(String text, Pageable pageable) {
        String name = text == null ? "" : text;
        return holidayRepository.searchAndPagination(name.toUpperCase(), pageable);
    }
    private User getAuthUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}