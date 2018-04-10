package studentInformation.service;

import java.util.List;

import studentInformation.model.Employee;

public interface EmployeeService {

	/*
	 * Find all the employees.
	 * @param None
	 * @Return List of all the employee's objects.
	 */
	public List<Employee> getAll();
	
	/*
	 * Save the Employee object
	 * @param Employee Object
	 * @Return Employee's object.
	 */
	public Employee save(Employee employee) throws Exception;
}
