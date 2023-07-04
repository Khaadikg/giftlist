package com.peaksoft.giftlistm5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
            SpringApplication.run(Application.class, args);
        System.out.println("Welcome colleagues, project name is Giftlist-M5!");
    }

    @GetMapping("/")
    public String greetingPage() {
        return "<h1>Welcome to Giftlist-M5 Application!!!<h1/>";
    }
}
