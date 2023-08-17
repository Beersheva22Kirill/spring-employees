package telran.spring.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Validated
public class Employee implements Serializable{

	private static final long serialVersionUID = 1L;
	
	Integer id;
	@NotEmpty
	String name;
	@NotEmpty
	String gender;
	@NotEmpty
	String birthDate;
	@NotEmpty
	String department;
	@Min(0)
	Double salary;
	
	public Employee(@NotEmpty String name, @NotEmpty String gender, @NotEmpty String birthDate,
			@NotEmpty String department, @Min(0) Double salary) {
		this.name = name;
		this.gender = gender;
		this.birthDate = birthDate;
		this.department = department;
		this.salary = salary;
	}

}
