package com.luv2code.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	private EmployeeService employeeService;

	@Autowired
	public EmployeeRestController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping("/employees")
	public List<Employee> findAll(){
		return employeeService.findAll();
	}

	@GetMapping("/employees/{id}")
	public Employee findEmployeeById(@PathVariable int id){
		Employee e = employeeService.findById(id);

		if (e == null) {
			throw new RuntimeException("Employee not found - "+id);
		}
		return e;
	}
	
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee e){
		e.setId(0);
		
		employeeService.save(e);

		return e;
	}
	
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee e){
		
		employeeService.save(e);

		return e;
	}
	
	@DeleteMapping("/employees/{id}")
	public String deleteEmployee(@PathVariable int id){
		Employee e = employeeService.findById(id);
		employeeService.deleteById(id);
		return "id deleted: "+id;
	}

	
}
