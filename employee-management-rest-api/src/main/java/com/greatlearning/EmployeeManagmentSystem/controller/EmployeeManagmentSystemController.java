package com.greatlearning.EmployeeManagmentSystem.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.greatlearning.EmployeeManagmentSystem.entity.Employee;
import com.greatlearning.EmployeeManagmentSystem.service.EmployeeService;

@Controller
@RequestMapping("/api")
public class EmployeeManagmentSystemController {
	@Autowired
	private EmployeeService employeeService;

	// Save or Create Operation
	@PostMapping("/employee")
	@ResponseBody
	public Employee roleAddSave(@Validated @RequestBody Employee employee) {
		return employeeService.saveEmployee(employee);

	}

	// Read Operations
	@GetMapping("/employee")
	@ResponseBody
	public List<Employee> userRead() {

		return employeeService.fetchEmployeeList();

	}

	// Update Operation
	@PutMapping("/employee/{Id}")
	@ResponseBody
	public Employee updateEmployee(@RequestBody Employee employee, @PathVariable("Id") Integer Id) {
		return employeeService.updateEmployee(employee, Id);

	}

	// Search Employee by ID Operation
	@GetMapping("/employee/{Id}")
	@ResponseBody
	public Employee getEmployeeById(@PathVariable("Id") Integer Id) {

		return employeeService.fetchEmployeeById(Id);

	}

	// Delete Operation
	@DeleteMapping("/employee/{Id}")
	@ResponseBody

	public String deleteEmployeeById(@PathVariable("Id") Integer Id) {

		employeeService.deleteEmployeeById(Id);
		return "Deleted employee id - " + Id;

	}

	// Search By First Name Operations
	@GetMapping("/employee/search/{FirstName}")
	@ResponseBody
	public List<Employee> serachByFristName(@PathVariable("FirstName") String FirstName) {

		return employeeService.fetchEmployeeListByFirstName(FirstName);

	}

	// Sort By First Name Operations
	@GetMapping("/employee/sort")
	@ResponseBody
	public List<Employee> sortByFristName(@RequestParam String order) {

		return employeeService.fetchEmployeeListSorted(order);

	}

}
