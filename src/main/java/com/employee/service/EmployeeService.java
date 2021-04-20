package com.employee.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.employee.dto.EmployeeDto;
import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

import static com.employee.utils.Constants.*;

import static com.employee.entity.Action.*;
import static com.employee.entity.ModuleName.*;

@Service
@RequiredArgsConstructor
public class EmployeeService {

	private final EmployeeRepository repository;
	
	private final ActionLogService actionLogService;
	
	private final EntityManager em;

	@Transactional
	public Employee save(Employee employee) {
		
		Employee e = repository.save(employee);
		actionLogService.publishActivity(
				SAVE,
				EMPLOYEE,
				e.getId(),
				EMPLOYEE_SAVE_COMMENTS
		);
		return e;
	}
	
	/*public void save(Employee employee) {
		em.persist(employee);
	}*/

	@Transactional
	public Employee update(Employee employee) {
		Employee e = repository.save(employee);
		actionLogService.publishActivity(
				UPDATE,
				EMPLOYEE,
				e.getId(),
				EMPLOYEE_UPDATE_COMMENTS
		);
		return e;
	}
	
	/*public void update(Employee employee) {
		em.merge(employee);
	}*/

	public Optional<Employee> findById(Long id) {
		return repository.findById(id);
	}

	public List<EmployeeDto> findAll() {
		return repository.getAll();
	}
	
	public List<Employee> findByEmail(String email) {
		return repository.findByEmail(email);
	}
	
	public EmployeeView findViewByName(String name) {
		return repository.findViewByName(name);
	}
	
	//findOneById && getOne()
	
	/*public List<EmployeeDto> findAll() {
		
		String sqlQuery ="SELECT "
				+ "new com.employee.dto.EmployeeDto("
				+ " e.email"
				+ " )"
				+ " FROM Employee e";
		
		//Query query = em.createQuery(sqlQuery);
		TypedQuery<EmployeeDto> query = em.createQuery(sqlQuery,EmployeeDto.class);
		List<EmployeeDto> lists = query.getResultList();
		return lists;
	}*/
	
	public void delete(Employee employee) {
		actionLogService.publishActivity(
				DELETE,
				EMPLOYEE,
				employee.getId(),
				EMPLOYEE_DELETE_COMMENTS
		);
		repository.delete(employee);
	}
}
