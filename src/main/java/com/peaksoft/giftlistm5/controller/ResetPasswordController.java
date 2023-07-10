package com.peaksoft.giftlistm5.controller;

import com.peaksoft.giftlistm5.model.User;
import com.peaksoft.giftlistm5.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class ResetPasswordController {
    private final EmailService emailService;

    @Autowired
    public ResetPasswordController(EmailService emailService) {
        this.emailService = emailService;
    }
    @GetMapping("/resetPassword")
    public String sendEmail(@RequestParam String email) {
        User user = emailService.resetPassword(email);
        emailService.sendSimpleMessage(user.getPinCode(), email);
        return user.getEmail();
    }
    @GetMapping("/changePassword")
    public String changePassword(@RequestParam int pinCode,
                                 @RequestParam String password,
                                 @RequestParam String passwordConfirm,
                                 @RequestParam String email) {
        return emailService.checkPinCode(pinCode, email, password, passwordConfirm);
    }}
