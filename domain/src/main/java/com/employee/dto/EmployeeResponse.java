package com.employee.dto;

import static com.employee.utils.StringUtils.nonNull;

import java.util.List;

import com.employee.entity.Address;
import com.employee.entity.Employee;

import lombok.Data;
import lombok.NoArgsConstructor;;

@Data
@NoArgsConstructor
public class EmployeeResponse {

	private Long id;

	private String email;

	private String name;

	private List<Address> addresses;

	private String department;

	public static EmployeeResponse from(Employee employee) {

		EmployeeResponse dto = new EmployeeResponse();
		dto.setId(employee.getId());
		dto.setEmail(employee.getEmail());
		dto.setName(employee.getName());
		dto.setAddresses(employee.getAddresses());
		dto.setDepartment(nonNull(employee.getDepartment()) ? employee.getDepartment().getName() : "No data");
		return dto;
	}
}

