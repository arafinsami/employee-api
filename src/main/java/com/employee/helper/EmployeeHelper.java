package com.employee.helper;

import org.springframework.stereotype.Component;

import com.employee.command.EmployeeCommand;
import com.employee.entity.Employee;

@Component
public class EmployeeHelper {

	public EmployeeCommand create() {
		return new EmployeeCommand(new Employee());
	}
}
