package com.peaksoft.giftlistm5.service;

import com.peaksoft.giftlistm5.exception.NotFoundException;
import com.peaksoft.giftlistm5.model.User;
import com.peaksoft.giftlistm5.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private UserRepository repository;
    private final BCryptPasswordEncoder encoder;


    public void sendSimpleMessage(int stringPinCode, String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        String pinCode = String.valueOf(stringPinCode);
        message.setTo(email);
        message.setFrom("moloshovamalika9@gmail.com");
        message.setSubject("Password change");
        message.setText(pinCode);
        javaMailSender.send(message);


    }

    public int pinCode() {
        Random random = new Random();
        int pinCode = random.nextInt(1000, 9999);
        return pinCode;
    }

    public String checkPinCode(int pinCheck, String email, String password, String confirm) {
        if (password.length() < 8)
            return "The password must contain one capital letter and one character and longer then 8 characters!";
        if (!confirm.equals(password)) {
            return "NOT EQUALS";
        } else {
            User user = repository.findByEmail(email).orElseThrow(
                    () -> new NotFoundException("User not found by email = " + email)
            );
            if (pinCheck == user.getPinCode() && user.getPinCode() != 0) {
                user.setPassword(encoder.encode(password));
                repository.save(user);
                return "SUCCESS";
            }
        }
        return "FAIL";
    }

    public User resetPassword(String email) {
        User user = repository.findByEmail(email).orElseThrow(
                () -> new NotFoundException("User not found by email = " + email)
        );
        user.setPinCode(pinCode());
        return repository.save(user);
    }

}
