package com.employee.service;

import static com.employee.enums.Action.DELETE;
import static com.employee.enums.Action.SAVE;
import static com.employee.enums.Action.UPDATE;
import static com.employee.enums.ModuleName.EMPLOYEE;
import static com.employee.utils.Constants.EMPLOYEE_DELETE_COMMENTS;
import static com.employee.utils.Constants.EMPLOYEE_SAVE_COMMENTS;
import static com.employee.utils.Constants.EMPLOYEE_UPDATE_COMMENTS;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.config.CacheNamespaceHandler;
import org.springframework.stereotype.Service;

import com.employee.dto.EmployeeDto;
import com.employee.dto.EmployeeView;
import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepository;
import com.employee.utils.PaginationParameters;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {

	private final EmployeeRepository repository;

	private final ActionLogService actionLogService;

	private final EntityManager em;

	@Transactional
	public Employee save(Employee employee) {

		Employee e = repository.save(employee);
		actionLogService.publishActivity(SAVE, EMPLOYEE, e.getId(), EMPLOYEE_SAVE_COMMENTS);
		return e;
	}

	/*
	 * public void save(Employee employee) { em.persist(employee); }
	 */

	@Transactional
	public Employee update(Employee employee) {
		Employee e = repository.save(employee);
		actionLogService.publishActivity(UPDATE, EMPLOYEE, e.getId(), EMPLOYEE_UPDATE_COMMENTS);
		return e;
	}

	/*
	 * public void update(Employee employee) { em.merge(employee); }
	 */

	public Optional<Employee> findById(Long id) {
		return repository.findById(id);
	}

	public List<EmployeeDto> findAll() {
		return repository.getAll();
	}
	
	public List<Employee> findAllEmployees() {
		return repository.findAll();
	}

	public List<Employee> findByEmail(String email) {
		return repository.findByEmail(email);
	}

	public Optional<Employee> findByName(String name) {
		return repository.findByName(name);
	}

	public EmployeeView findViewByName(String name) {
		return repository.findViewByName(name);
	}

	// findOneById && getOne()

	/*
	 * public List<EmployeeDto> findAll() {
	 * 
	 * String sqlQuery ="SELECT " + "new com.employee.dto.EmployeeDto(" + " e.email"
	 * + " )" + " FROM Employee e";
	 * 
	 * //Query query = em.createQuery(sqlQuery); TypedQuery<EmployeeDto> query =
	 * em.createQuery(sqlQuery,EmployeeDto.class); List<EmployeeDto> lists =
	 * query.getResultList(); return lists; }
	 */

	public void delete(Employee employee) {
		actionLogService.publishActivity(DELETE, EMPLOYEE, employee.getId(), EMPLOYEE_DELETE_COMMENTS);
		repository.delete(employee);
	}

	public Map<String, Object> getAllEmployees(Integer page, Integer size) {

		Map<String, Object> maps = new HashMap<>();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
		Root<Employee> root = query.from(Employee.class);

		query.select(root);

		TypedQuery<Employee> tQuery = em.createQuery(query).setFirstResult(page * size).setMaxResults(size);

		List<Employee> employees = tQuery.getResultList();

		Long total = (long) employees.size();

		PaginationParameters.getdata(maps, page, total, size, employees);

		return maps;
	}
}

