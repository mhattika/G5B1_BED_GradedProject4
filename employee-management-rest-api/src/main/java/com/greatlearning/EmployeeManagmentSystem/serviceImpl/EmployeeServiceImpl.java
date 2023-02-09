package com.greatlearning.EmployeeManagmentSystem.serviceImpl;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.greatlearning.EmployeeManagmentSystem.entity.Employee;
import com.greatlearning.EmployeeManagmentSystem.repository.EmployeeRepository;
import com.greatlearning.EmployeeManagmentSystem.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	// Save operation
	@Override
	public Employee saveEmployee(Employee employee) {

		return employeeRepository.save(employee);
	}

	// Read operation
	@Override
	public List<Employee> fetchEmployeeList() {

		return (List<Employee>) employeeRepository.findAll();
	}

	// Update operation
	@Override
	public Employee updateEmployee(Employee employee, Integer Id) {

		Employee employeeDB = employeeRepository.findById(Id).get();
		if (Objects.nonNull(employee.getFirstName()) && !"".equalsIgnoreCase(employee.getLastName())) {
			employeeDB.setFirstName(employee.getFirstName());
			employeeDB.setLastName(employee.getLastName());
			employeeDB.setEmail(employee.getEmail());
		}

		return employeeRepository.save(employeeDB);
	}

	// Delete operation by ID
	@Override
	public void deleteEmployeeById(Integer Id) {
		employeeRepository.deleteById(Id);

	}

	// Employee search by ID
	@Override
	public Employee fetchEmployeeById(Integer Id) {

		return employeeRepository.findById(Id).get();
	}

	// Employee search by First Name
	@Override
	public List<Employee> fetchEmployeeListByFirstName(String FirstName) {

		return employeeRepository.getEmployeeByFirstName(FirstName);
	}

	// Employee Record Sorting by First Name
	@Override
	public List<Employee> fetchEmployeeListSorted(String order) {

		if (order.equals("asc")) {

			return (List<Employee>) employeeRepository.findAll(Sort.by("firstName").ascending());

		}
		if (order.equals("desc")) {

			return (List<Employee>) employeeRepository.findAll(Sort.by("firstName").descending());
		}
		return null;
	}

}
