package com.smart_ski_rent_ver1_2.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class MessagesController {

    @GetMapping("/messages")
    public ResponseEntity<String> getMessage(@RequestParam String type) {
        if ("login".equals(type)) {
            return ResponseEntity.ok("Zalogowano pomyślnie");
        } else if ("register".equals(type)) {
            return ResponseEntity.ok("Zarejestrowano pomyślnie");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nieznana akcja");
        }
    }
}
