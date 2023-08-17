package telran.spring.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.provisioning.UserDetailsManager;

import telran.spring.model.Employee;
@SpringBootTest(classes = {EmployeesServiceRest.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeesServiceTest {

	@Autowired
	EmployeesServiceRest service;
	
	
	@AfterAll
	static void deleteFileAfter() throws IOException {
		
		Files.deleteIfExists(Path.of("empl_test.data"));
	}
	@BeforeAll
	static void deleteFileBefore() throws IOException {
		
		Files.deleteIfExists(Path.of("empl_test.data"));
	}
	
	@Test
	@Order(1)
	void addEmployeeTest() {
		Employee[] emplArr = new Employee[] {
				new Employee("test_name", "male", "04.04.1989", "test_dep", 20000d),
				new Employee("test_name1", "male", "04.04.1989", "test_dep", 20000d),
				new Employee("test_name2", "male", "04.04.1989", "test_dep", 20000d)
		};
		for (int i = 0; i < emplArr.length; i++) {
			emplArr[i].setId(i);
		}
		assertEquals(0, service.getAllEmployees().size());
		for (Employee employee : emplArr) {
			Integer id = service.addEmployee(employee);
			assertNotNull(id);
		}
		assertEquals(3, service.getAllEmployees().size());		
	}
	
	@Test
	@Order(2)
	void deleteEmployeetest() {
		Employee expected = new Employee("test_name", "male", "04.04.1989", "test_dep", 20000d);
		expected.setId(0);
		Employee actual = service.deleteEmployee(0);
		assertTrue(actual.equals(expected));
		assertEquals(2, service.getAllEmployees().size());
	}

}
