package telran.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import telran.spring.model.Employee;
import telran.spring.service.EmployeesService;


@RestController
@RequestMapping("/employees")
@Slf4j
@CrossOrigin
public class EmployeesController implements EmployeesService{
	
	@Autowired
	EmployeesService service;
	
	@PostMapping
	public Integer addEmployee(@RequestBody Employee employee) {
		log.trace("Controller recieved employee: {}", employee);
		return service.addEmployee(employee);
	}

	@DeleteMapping({"id"})
	public Employee deleteEmployee(@PathVariable Integer id) {
		log.trace("Controller recieved for remove id: {}", id);
		return service.deleteEmployee(id);
	}

	@GetMapping("{id}")
	public Employee getEmployee(@PathVariable Integer id) {
		
		return service.getEmployee(id);
	}

	@GetMapping
	public List<Employee> getAllEmployees() {
		
		return service.getAllEmployees();
	}

	@PutMapping("{id}")
	public Employee updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
		log.trace("Controller recieved id {} for update: {}",id , employee);
		return service.updateEmployee(id, employee);
	}

	@PreDestroy
	@Override
	public void save() {
		service.save();
		
	}

	@PostConstruct
	@Override
	public void restore() {
		service.restore();	
	}


}
