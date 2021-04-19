package com.employee.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {

	private final EmployeeRepository repository;

	@Transactional
	public Employee save(Employee employee) {
		Employee e = repository.save(employee);
		return e;
	}

	@Transactional
	public Employee update(Employee employee) {
		Employee e = repository.save(employee);
		return e;
	}

	public Optional<Employee> findById(Long id) {
		return repository.findById(id);
	}

	public List<Employee> findAll() {
		return repository.findAll();
	}
	
	public void delete(Employee employee) {
		repository.delete(employee);
	}
}
