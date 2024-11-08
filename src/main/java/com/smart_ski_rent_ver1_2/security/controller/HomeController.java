package com.smart_ski_rent_ver1_2.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "Witaj w aplikacji!";
    }

    @GetMapping("/favicon.ico")
    public void favicon() {
        // Możemy zwrócić pustą odpowiedź lub skomentować ten metodę, aby nie powodowała błędów.
    }
}
