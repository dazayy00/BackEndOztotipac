package com.oztotipac.org.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DemoController {

    @PostMapping(value = "demo")
    public String welcome() {
        return "welcome form secure endpoint";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String adminEndpoint() {
        return "Admin access only";
    }

    @PreAuthorize("hasRole('SUP')")
    @GetMapping("/supervisor")
    public String supervisorEndpoint() {
        return "Supervisor access only";
    }

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping("/client")
    public String clientEndpoint() {
        return "Client access only";
    }

}
