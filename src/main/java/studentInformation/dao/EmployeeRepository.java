package studentInformation.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import studentInformation.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}
