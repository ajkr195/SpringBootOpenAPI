package com.springboot.employee_app.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.employee_app.data.models.Employee;
import com.springboot.employee_app.data.payloads.request.EmployeeRequest;
import com.springboot.employee_app.data.payloads.response.MessageResponse;
import com.springboot.employee_app.service.EmployeeService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/employee")
//Line 19 to 24 is the newly added code for swagger documentation
@ApiResponses(value = {
		@ApiResponse(responseCode = "400", description = "This is a bad request, please follow the API documentation for the proper request format"),
		@ApiResponse(responseCode = "401", description = "Due to security constraints, your access request cannot be authorized"),
		@ApiResponse(responseCode = "500", description = "The server is down. Please bear with us."), })
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping("/all")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> footballers = employeeService.getAllEmployee();
		return new ResponseEntity<>(footballers, HttpStatus.OK);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Integer id) {
		Employee employee = employeeService.getASingleEmployee(id);
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<MessageResponse> addEmployee(@RequestBody EmployeeRequest employee) {
		MessageResponse newEmployee = employeeService.createEmployee(employee);
		return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public Optional<Employee> updateEmployee(@PathVariable Integer id, @RequestBody EmployeeRequest employee) {
		return employeeService.updateEmployee(id, employee);

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable("id") Integer id) {
		employeeService.deleteEmployee(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
