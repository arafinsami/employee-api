package com.employee.controller;

import static com.employee.response.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.ok;

import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.dto.EmployeeDto;
import com.employee.entity.Employee;
import com.employee.exception.NotFoundException;
import com.employee.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Api(tags = "Employees's Data")
public class EmployeeController {

	private final EmployeeService service;

	@PostMapping("/save")
	@ApiOperation(value = "save employee", response = EmployeeDto.class)
	public ResponseEntity<JSONObject> save(@RequestBody EmployeeDto dto) {

		Employee employee = dto.to();

		service.save(employee);
		return ok(success(EmployeeDto.from(employee)).getJson());
	}

	@PutMapping("/update")
	@ApiOperation(value = "update employee", response = EmployeeDto.class)
	public ResponseEntity<JSONObject> update(@RequestBody EmployeeDto dto) {

		Employee employee = service.findById(dto.getId()).orElseThrow(NotFoundException::new);

		dto.update(employee);
		service.update(employee);
		return ok(success(EmployeeDto.from(employee)).getJson());
	}

	@DeleteMapping("/delete/{id}")
	@ApiOperation("delete employee")
	public ResponseEntity<JSONObject> delete(@PathVariable Long id) {

		Employee employee = service.findById(id).orElseThrow(NotFoundException::new);
		service.delete(employee);
		return ok(success("employee deleted with id : " + employee.getId()).getJson());
	}
}
