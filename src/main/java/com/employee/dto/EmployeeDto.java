package com.employee.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.employee.entity.Address;
import com.employee.entity.Department;
import com.employee.entity.Employee;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeDto {

	private Long id;

	@Email
	@NotBlank
	private String email;

	@NotBlank
	private String name;

	private Long department;

	private List<Address> addresses;

	public Employee to() {

		Employee employee = new Employee();
		employee.setName(name);
		employee.setEmail(email);
		return employee;
	}

	public void update(Employee employee) {

		employee.setName(name);
		employee.setEmail(email);
	}

	public static EmployeeDto from(Employee employee) {

		EmployeeDto dto = new EmployeeDto();
		dto.setId(employee.getId());
		dto.setEmail(employee.getEmail());
		dto.setName(employee.getName());
		dto.setAddresses(employee.getAddresses());
		return dto;
	}

	public EmployeeDto(String email) {
		this.email = email;
	}
}
