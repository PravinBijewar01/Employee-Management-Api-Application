package com.uk.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.uk.model.Employee;

@Repository
public class EmployeeRepository {


	public List<Employee> findAll(){
		return Arrays.asList(
				new Employee(1,"Amit","HR","Manager",75000),
				new Employee(2,"Neha","IT","Developer",90000),
				new Employee(3,"Rahul","IT","Developer",80000),
				new Employee(4,"Simran","Finance","Analyst",70000),
				new Employee(5,"Vikash","HR","Recuter",50000),
				new Employee(6,"Sumit","Marketing","mart",60000),
				new Employee(7,"Neetin","Marketing","mart",65000)

				);
	}
}
