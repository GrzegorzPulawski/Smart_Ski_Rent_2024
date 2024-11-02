package com.smart_ski_rent_ver1_2.security.newuserrole;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FartController {

    @GetMapping("/user/farts")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<MessageDto> farts() { return ResponseEntity.ok(new MessageDto("farts"));
    }
    @GetMapping("/devel/farts")
    @PreAuthorize("hasAuthority('DEVEL')")
    public ResponseEntity<MessageDto> farts() { return ResponseEntity.ok(new MessageDto("DEVELOP'S farts"));
    }
    @GetMapping("/admin/farts")
    @PreAuthorize("hasAuthority('AMIN')")
    public ResponseEntity<MessageDto> farts() { return ResponseEntity.ok(new MessageDto("ADMIN'S farts"));
    }
}
