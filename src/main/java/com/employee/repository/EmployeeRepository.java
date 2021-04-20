package com.employee.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.employee.dto.EmployeeDto;
import com.employee.entity.Employee;
import com.employee.service.EmployeeView;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Optional<Employee> findById(Long id);
	
	/*@Query(value = "SELECT * FROM employee",nativeQuery = true)
	List<Employee> getAll();*/
	
	//@Query("SELECT e FROM Employee e")
	@Query("SELECT new com.employee.dto.EmployeeDto(e.email) FROM Employee e")
	List<EmployeeDto> getAll();
	
	List<Employee> findByEmail(String email);
	
	EmployeeView findViewByName(String name);
 }
