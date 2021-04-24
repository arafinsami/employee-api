package com.employee.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.employee.entity.Department;
import com.employee.repository.DepartmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentService {

	private final DepartmentRepository repository;
	
	public Optional<Department> findById(Long id){
		return repository.findById(id);
	}
}
