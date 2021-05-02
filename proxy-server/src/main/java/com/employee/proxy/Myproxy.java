package com.employee.proxy;

import org.json.simple.JSONObject;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "apigateway")
@RibbonClient(name = "employee-api")
public interface Myproxy {

	@GetMapping("/employee-api/employee/employee-list")
	public ResponseEntity<JSONObject> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "5") Integer size);
}
