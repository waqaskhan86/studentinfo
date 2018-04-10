package studentInformation.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import studentInformation.dao.EmployeeRepository;
import studentInformation.model.Employee;
import studentInformation.service.EmployeeService;

@Service(value = "employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	public EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getAll() {
		List<Employee> employees = new ArrayList<>();
		try {
			for (Employee employee : employeeRepository.findAll()) {
				employees.add(employee);
			}
		} catch (Exception e) {
			throw e;
		}
		return employees;
	}

	@Override
	public Employee save(Employee employee) throws Exception {
		return employeeRepository.save(employee);
	}

}
