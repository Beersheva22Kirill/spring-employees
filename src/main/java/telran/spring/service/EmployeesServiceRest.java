package telran.spring.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import telran.spring.exceptions.NotFoundException;
import telran.spring.model.Employee;

@Service
@Slf4j
public class EmployeesServiceRest implements EmployeesService {
	
	Map<Integer,Employee> map = new HashMap();
	@Value("${app.employees.file.name:Employees.data}")
	String fileName;
	Integer MIN_ID = 100000;
	Integer MAX_ID = 1000000;
	
	@Override
	public Integer addEmployee(Employee employee) {
		Integer id = employee.getId() != null ? employee.getId() : getRandomId();
		employee.setId(id);
		map.putIfAbsent(id, employee);
		log.trace("Employee added. id of employee: {}", id);
		return id;
	}

	private Integer getRandomId() {
		Integer id = null;
		do {
			id = getRandomInt(MIN_ID,MAX_ID);
		} while (exists(id));
		
		return id;
	}

	private boolean exists(Integer id) {
			Employee res = map.get(id);
		return res != null;
	}

	private Integer getRandomInt(Integer min, Integer max) {
		
		return (int) (min + Math.random() * (max - min));
	}

	@Override
	public Employee deleteEmployee(Integer id) {
		Employee removed = map.get(id);
		if(removed != null) {
			map.remove(id);
			log.trace("Employee with id - {} removed",id);
		}else {
			log.error("Employee with id: {} not found", id);
			throw new NotFoundException("Employee with id: " + id + "not found");
			
		}
		return removed;
	}

	@Override
	public Employee getEmployee(Integer id) {
		Employee employee = map.get(id);
		if(employee == null) {
			log.error("Employee with id: {} not found", id);
			throw new NotFoundException("Employee with id: " + id + "not found");
		}		
		return employee;
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		
		return new ArrayList<Employee>(map.values());
	}


	@Override
	public Employee updateEmployee(Integer id, Employee employee) {
		Employee updated = map.get(employee);
		if (updated != null) {
			map.remove(id);
			addEmployee(employee);
		}
		return null;
	}

	@Override
	public void save() {
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))){
			log.info("Begin save employees in file:{}",fileName);
			out.writeObject(getAllEmployees());
			log.info("Save in {} completed. Amount of employees:{}",fileName,map.size());
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		

	}

	@Override
	public void restore() {
		List<Employee> emplArr = new ArrayList<Employee>();
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))){
			log.info("Begin restore from file: {}", fileName);
			emplArr = (List<Employee>) ois.readObject();
			Iterator<Employee> iterator = emplArr.iterator();
			int count = 0;
			while (iterator.hasNext()) {
				addEmployee(iterator.next());
				count++;
			}
			log.info("Restore from file: {} completed. Restored {} employees",fileName,count);
		} catch (FileNotFoundException e) {
			log.error("File with name: {} not found", fileName);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}



}
