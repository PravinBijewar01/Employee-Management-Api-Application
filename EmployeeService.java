package com.uk.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uk.model.Employee;
import com.uk.repository.EmpRepo;
import com.uk.repository.EmployeeRepository;

import com.uk.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	@Autowired
	private EmpRepo empRepo;


	public List<Employee> getAllEmployees(){
		return empRepo.findAll();
	}


	public List<Employee> getEmployeesWithSalaryAbove(double amount){
		return
				empRepo.findAll().stream()
				.filter(e->e.getSalary()>amount)
				.collect(Collectors.toList());
	}

	public Map<String,List<Employee>> groupByDepartment(){
		return 
				empRepo.findAll().stream()
				.collect(Collectors.groupingBy(Employee::getDepartment));
	}



	public Map<String, Long> countByRole(){
		return
				empRepo.findAll().stream()
				.collect(Collectors.groupingBy(Employee::getRole,
						Collectors.counting()));	
	}


	public List<Employee> sortBySalaryDesc(){
		return 
				empRepo.findAll().stream()
				.sorted((e1, e2)->
				Double.compare(e2.getSalary(), e1.getSalary()))
				.collect(Collectors.toList());
	}



	public Employee addEmployee(Employee emp) {
		return empRepo.save(emp);
	}


	public Optional<Employee> getEmployeeById(int id){
		return empRepo.findById(id);
	}


	public Employee updateEmployee(int id, Employee emp) {
		return
				empRepo.findById(id).map(existing -> {
					existing.setName(emp.getName());
					existing.setRole(emp.getRole());
					existing.setDepartment(emp.getDepartment());
					existing.setSalary(emp.getSalary());

					return
							empRepo.save(existing);
				}).orElseThrow(()-> new RuntimeException("Employee not found"));
	}
}
