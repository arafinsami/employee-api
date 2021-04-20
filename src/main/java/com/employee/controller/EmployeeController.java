package com.employee.controller;

import static com.employee.exception.ApiError.fieldError;
import static com.employee.response.ResponseBuilder.error;
import static com.employee.response.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.dto.EmployeeDto;
import com.employee.entity.Employee;
import com.employee.exception.NotFoundException;
import com.employee.service.EmployeeService;
import com.employee.service.EmployeeView;

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
	public ResponseEntity<JSONObject> save(@Valid @RequestBody EmployeeDto dto, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return badRequest().body(error(fieldError(bindingResult)).getJson());
		}

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

	@GetMapping("/employees")
	@ApiOperation(value = "get all employee", response = EmployeeDto.class)
	public ResponseEntity<JSONObject> findAll() {

		List<EmployeeDto> dtos = service.findAll();
		return ok(success(dtos).getJson());
	}
	
	@GetMapping("/employees/{email}")
	@ApiOperation(value = "get all employee by email", response = EmployeeDto.class)
	public ResponseEntity<JSONObject> findByEmail(@PathVariable String email) {

		List<EmployeeDto> dtos = service.findByEmail(email).stream().map(EmployeeDto::from).collect(Collectors.toList());
		return ok(success(dtos).getJson());
	}
	
	@GetMapping("/employee/{name}")
	@ApiOperation(value = "get employee by name", response = EmployeeDto.class)
	public ResponseEntity<JSONObject> findViewByName(@PathVariable String name) {

		EmployeeView view = service.findViewByName(name);
		return ok(success(view).getJson());
	}
}
