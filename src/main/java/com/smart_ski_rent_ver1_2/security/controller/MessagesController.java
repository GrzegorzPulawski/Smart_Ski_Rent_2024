package com.smart_ski_rent_ver1_2.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class MessagesController {

    @GetMapping("/messages")
    public ResponseEntity<List<String>> messages() {
        return ResponseEntity.ok(Arrays.asList("first", "second"));
    }
}
