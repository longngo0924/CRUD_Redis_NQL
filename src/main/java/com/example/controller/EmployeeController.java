package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.entity.Employee;
import com.example.repository.EmployeeRepo;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping
public class EmployeeController {
	@Autowired
	private EmployeeRepo employeeRepository;

	@PostMapping("/employees")
	public Employee saveEmployee(@RequestBody Employee employee) {
		employeeRepository.saveEmployee(employee);
		return employee;
	}

	@GetMapping("/employees")
	public Set<Employee> findAll() {

		return employeeRepository.findAll();
	}

	@GetMapping("/employees/{id}")
	public Employee findById(@PathVariable("id") Integer id) {

		return employeeRepository.findById(id);
	}

	@PutMapping("/employee}")
	public void update(@RequestBody Employee employee) {

		employeeRepository.update(employee);
	}

	@DeleteMapping("/employees/{id}")
	public void delete(@PathVariable("id") Integer id) {
		employeeRepository.delete(id);
	}

}
