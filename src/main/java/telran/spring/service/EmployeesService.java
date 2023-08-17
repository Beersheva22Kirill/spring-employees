package telran.spring.service;

import java.util.List;

import telran.spring.model.Employee;

public interface EmployeesService {
	
	Integer addEmployee(Employee employee);
	Employee deleteEmployee(Integer id);
	Employee getEmployee(Integer id);
	List<Employee> getAllEmployees();
	Employee updateEmployee(Integer id, Employee employee);
	void save();
	void restore();

}
