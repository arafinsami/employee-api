package com.employee.dto;

import com.employee.entity.Employee;

import lombok.Data;

@Data
public class EmployeeDto {

	private Long id;

	private String email;

	private String name;

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
		return dto;
	}
}
