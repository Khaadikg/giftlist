package com.peaksoft.giftlistm5.service;

import com.peaksoft.giftlistm5.dto.CharityRequest;
import com.peaksoft.giftlistm5.dto.CharityResponse;
import com.peaksoft.giftlistm5.model.Charity;
import com.peaksoft.giftlistm5.model.User;
import com.peaksoft.giftlistm5.repository.CharityRepository;
import com.peaksoft.giftlistm5.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CharityService {
    @Autowired
    private final CharityRepository charityRepository;
    private final UserRepository userRepository;

    public CharityResponse getCharityById(Long id) {
        Charity charity = charityRepository.getReferenceById(id);
        return mapToResponse(charity);
    }

    public List<CharityResponse> getAllCharities(){
        List<CharityResponse> charityResponses = new ArrayList<>();
        for (Charity charity : charityRepository.findAll()) {
            charityResponses.add(mapToResponse(charity));
        }
        return charityResponses;
    }

//    public CharityResponse saveCharity(CharityRequest request) {
//
//        Charity charity = mapToCharity(request);
//        charityRepository.save(charity);
//
//        return mapToResponse(charity);
//    }
public CharityResponse saveCharity(Long ownerId) {
    User owner = userRepository.findById(ownerId).get();
    Charity charity = new Charity();
    for (Charity ownerCharity : owner.getCharities()) {
        charity.setId(ownerCharity.getId());
        charity.setOwner(owner);
        List<Charity> charities = new ArrayList<>();
        charities.add(ownerCharity);
        owner.setCharities(charities);
        charity.setCreatedDate(LocalDate.now());
        charity.setName(ownerCharity.getName());
        charity.setImage(ownerCharity.getImage());
        charity.setMainCategory(ownerCharity.getMainCategory());
        charity.setSubCategory(ownerCharity.getSubCategory());
    }
    charityRepository.save(charity);
    userRepository.save(owner);

    return mapToResponse(charity);

}
    public void deleteCharity(Long charityId) {
        charityRepository.deleteById(charityId);
    }

    public CharityResponse updateCharity(Long charityId, CharityRequest charityRequest) {
        Charity charity = charityRepository.findById(charityId).get();
        charity.setName(charityRequest.getName());
        charity.setImage(charityRequest.getImage());
        charity.setMainCategory(charityRequest.getMainCategory());
        charityRepository.save(charity);
        return mapToResponse(charity);


    }

    public CharityResponse mapToResponse(Charity charity) {
        CharityResponse charityResponse = new CharityResponse();
        charityResponse.setId(charity.getId());
        charityResponse.setName(charity.getName());
        charityResponse.setImage(charity.getImage());
        charityResponse.setMainCategory(charity.getMainCategory());
        charityResponse.setCreatedDate(charity.getCreatedDate());
        return charityResponse;
    }

//    public Charity mapToCharity(CharityRequest request) {
//        Charity charity = new Charity();
//        charity.setName(request.getName());
//        charity.setImage(request.getImage());
//        charity.setMainCategory(request.getMainCategory());
//        charity.setCreatedDate(LocalDate.now());
//        return charity;
    }

