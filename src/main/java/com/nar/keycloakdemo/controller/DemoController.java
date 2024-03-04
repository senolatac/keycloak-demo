package com.nar.keycloakdemo.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


public class DemoController {

    @GetMapping("/user")
    @PreAuthorize("hasRole('user')")
    public String getUser() {
        return "Hello from User";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('admin')")
    public String getAdmin() {
        return "Hello from Admin";
    }
}
