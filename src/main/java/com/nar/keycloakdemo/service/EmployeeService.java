package com.nar.keycloakdemo.service;

import com.nar.keycloakdemo.dto.EmployeeRecord;
import com.nar.keycloakdemo.mapper.EmployeeMapper;
import com.nar.keycloakdemo.model.Employee;
import com.nar.keycloakdemo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Transactional(readOnly = true)
    public List<EmployeeRecord> getAllEmployees() {
        return employeeRepository.findAll().stream().map(employeeMapper::mapToEmployeeRecordFromEmployee).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<EmployeeRecord> readEmployeeById(long id) {
        return employeeRepository.findById(id).map(employeeMapper::mapToEmployeeRecordFromEmployee);
    }

    @Transactional
    public EmployeeRecord createEmployee(EmployeeRecord employeeRecord) {
        Employee employee = employeeMapper.mapToEmployeeFromEmployeeRecord(employeeRecord);
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.mapToEmployeeRecordFromEmployee(savedEmployee);
    }

    @Transactional
    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }
}
