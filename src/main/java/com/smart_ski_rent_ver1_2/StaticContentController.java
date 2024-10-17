package com.smart_ski_rent_ver1_2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticContentController {

    @GetMapping("/")
    public String home() {
        return "index"; // Zwróci plik index.html z katalogu static
    }

    @GetMapping("/other-page")
    public String otherPage() {
        return "other-page"; // Zwróci plik other-page.html z katalogu static
    }
}

