package com.peaksoft.giftlistm5.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {
    @Bean
    public JavaMailSender getJavaMailSender(){
        JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("moloshovamalika9@gmail.com");
        mailSender.setPassword("yodvjatyaccsndtj");

        Properties properties=mailSender.getJavaMailProperties();
        properties.put("mail.transport.protocol","smtp");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.debug","true");
        return mailSender;
    }
}
