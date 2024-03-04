package com.nar.keycloakdemo.controller;


import com.nar.keycloakdemo.dto.UserRecord;
import com.nar.keycloakdemo.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "Keycloak")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{realm}")
    public ResponseEntity<List<UserRecord>> getAllUsers(@PathVariable String realm) {
        return ResponseEntity.ok(userService.getAllUsers(realm));
    }

    @PostMapping("/{realm}")
    public ResponseEntity<UserRecord> createUser(@RequestBody UserRecord userRecord, @PathVariable String realm) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userRecord, realm));
    }

}
