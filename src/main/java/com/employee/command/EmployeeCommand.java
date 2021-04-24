package com.employee.command;

import javax.validation.Valid;

import com.employee.entity.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCommand {

	@Valid
	private Employee employee;
}
