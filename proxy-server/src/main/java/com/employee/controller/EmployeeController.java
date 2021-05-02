package com.employee.controller;

import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.proxy.Myproxy;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Api(tags = "Employees's Data")
@RequestMapping("/employee")
public class EmployeeController {

	private final Myproxy proxy;

	@GetMapping("/employee-list")
	public ResponseEntity<JSONObject> getAllEmployees(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "5") Integer size
			) {

		ResponseEntity<JSONObject> response = proxy.findAll(page, size);
		return response;
	}
}
