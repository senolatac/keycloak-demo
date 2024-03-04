package com.nar.keycloakdemo.mapper;

import com.nar.keycloakdemo.dto.EmployeeRecord;
import com.nar.keycloakdemo.model.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeRecord mapToEmployeeRecordFromEmployee(Employee employee);
    Employee mapToEmployeeFromEmployeeRecord(EmployeeRecord employeeRecord);
}
