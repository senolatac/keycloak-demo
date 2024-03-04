package com.nar.keycloakdemo.controller;

import com.nar.keycloakdemo.dto.EmployeeRecord;
import com.nar.keycloakdemo.service.EmployeeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@SecurityRequirement(name = "Keycloak")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeRecord>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeRecord> getEmployeeById(@PathVariable long id) {
        return ResponseEntity.of(employeeService.readEmployeeById(id));
    }

    @PostMapping
    public ResponseEntity<EmployeeRecord> createEmployee(@RequestBody EmployeeRecord employeeRecord) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.createEmployee(employeeRecord));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
