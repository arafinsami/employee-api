package com.employee.validator;

import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.employee.dto.EmployeeDto;
import com.employee.entity.Employee;
import com.employee.exception.NotFoundException;
import com.employee.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmployeeValidator implements Validator {

	private final EmployeeService service;

	@Override
	public boolean supports(Class<?> clazz) {
		return EmployeeDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		EmployeeDto dto = (EmployeeDto) target;

		Employee employee = service.findByName(dto.getName()).orElseThrow(NotFoundException::new);

		if (Objects.nonNull(employee)) {
			errors.rejectValue("name", "error.employee.name.exist", "name already exist");
		}
	}

}
