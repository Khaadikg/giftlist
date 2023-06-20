package com.peaksoft.giftlistm5.registration;

import com.peaksoft.giftlistm5.registration.RegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    public String register(RegistrationRequest request) {
        return  "works";
    }
}
