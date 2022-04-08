package com.springboot.employee_app.service;

import com.springboot.employee_app.data.models.Employee;
import com.springboot.employee_app.data.payloads.request.EmployeeRequest;
import com.springboot.employee_app.data.payloads.response.MessageResponse;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface EmployeeService {
    MessageResponse createEmployee(EmployeeRequest employeeRequest);
    Optional<Employee> updateEmployee(Integer employeeId, EmployeeRequest employeeRequest);
    void deleteEmployee(Integer employeeId);
    Employee getASingleEmployee(Integer employeeId);
    List<Employee> getAllEmployee();
}
