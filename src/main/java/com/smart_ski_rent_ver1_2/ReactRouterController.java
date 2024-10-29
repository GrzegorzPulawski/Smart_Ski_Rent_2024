package com.smart_ski_rent_ver1_2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReactRouterController {
    @RequestMapping("/{path:^(?!api|static|.*\\.js$).*$}")
    public String redirect() {
        return "forward:/";
    }
}
