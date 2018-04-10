package studentInformation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import studentInformation.model.ContractualEmployee;
import studentInformation.model.Employee;
import studentInformation.model.PermanentEmployee;
import studentInformation.service.impl.EmployeeServiceImpl;
import studentInformation.service.impl.StudentServiceImpl;

@RestController
@RequestMapping("/employees")
public class EmployeeRestController {
	
	@Autowired
	private EmployeeServiceImpl employeeService;

	@GetMapping("/")
	public String allEmployees() throws Exception {
		try {
			return employeeService.getAll().toString();
		} catch (Exception e) {
			throw new Exception("Did not find any employees.");
		}
	}

	@PostMapping("/add")
	public Employee createEmployee(@RequestBody Employee empl) throws Exception {
		Employee empObject = new Employee();
		empObject.setFirstName(empl.getFirstName());
		empObject.setLastName(empl.getLastName());
//		PermanentEmployee pEmployee = new PermanentEmployee();
//		pEmployee.setIsPermanentEmployee(true);
//		pEmployee.setFirstName(empl.getFirstName());
//		pEmployee.setLastName(empl.getLastName());
//		ContractualEmployee cEmployee = new ContractualEmployee();
//		cEmployee.setIsContractualEmployee(true);
//		cEmployee.setFirstName(empl.getFirstName());
//		cEmployee.setLastName(empl.getLastName());
		try {
			employeeService.save(empObject);
		} catch (Exception ex) {
			throw ex;
		}
		return empl;
	}

}
