package com.peaksoft.giftlistm5.controller;

import com.peaksoft.giftlistm5.dto.CharityRequest;
import com.peaksoft.giftlistm5.dto.CharityResponse;
import com.peaksoft.giftlistm5.model.Charity;
import com.peaksoft.giftlistm5.service.CharityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/charities")
public class CharityController {
    private final CharityService charityService;
    @GetMapping
    public CharityResponse getCharityById(@PathVariable("id")Long id){
        return charityService.getCharityById(id);

    }
    @GetMapping("allCharities")
    public List<CharityResponse> getAllCharities(){
        return charityService.getAllCharities();
    }

//@PostMapping("/charity")
//public CharityResponse saveCharity (@RequestBody CharityRequest charityRequest){
//    return charityService.saveCharity(charityRequest);
    @PostMapping("/charity")
public CharityResponse saveCharity (@RequestBody Long ownerId){
        return charityService.saveCharity(ownerId);
}

    @PutMapping("{id}")
    public CharityResponse updateCharity(@PathVariable Long id, @RequestBody CharityRequest charityRequest){
        return charityService.updateCharity(id,charityRequest);
    }
    @DeleteMapping("{id}")
    public String delete(@PathVariable("id")Long id){
        charityService.deleteCharity(id);
        return "Successfully Cherity  deleted with id : " + id;
    }

}
