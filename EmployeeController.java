package com.uk.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uk.model.Employee;
import com.uk.service.EmployeeService;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	@GetMapping
	public List<Employee> getAll(){
		return service.getAllEmployees();	
	}

	@GetMapping("/salaryAbove/{amount}")
	public List<Employee> getHighSalary(@PathVariable double amount){
		return
				service.getEmployeesWithSalaryAbove(amount);
	}


	@GetMapping("/groupByDepartment")
	public Map<String, List<Employee>> groupByDept(){
		return
				service.groupByDepartment();	
	}

	@GetMapping("/countByRole")
	public Map<String,Long> countByRole(){
		return
				service.countByRole();
	}

	@GetMapping("/sortBySalary")
	public List<Employee> sortBySalary(){
		return
				service.sortBySalaryDesc();
	}

	@PostMapping
	public Employee create(@RequestBody Employee emp) {
		return service.addEmployee(emp);
	}


	@GetMapping("/{id}")
	public ResponseEntity<Employee> getOne(@PathVariable int id){
		return 
				service.getEmployeeById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public Employee update(@PathVariable int id, @RequestBody Employee emp) {
		return 
				service.updateEmployee(id, emp);
	}
}
