package studentInformation.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import studentInformation.model.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer>, JpaSpecificationExecutor<Student>  {

	/**
	 * Finds Students by using the last name as a search criteria.
	 * @param student_last_name
	 * @return A list of students which last name is an exact match with the given
	 *         last name. If no student is found, this method returns an empty list.
	 */
	// @Query("SELECT s.student_first_name, c.city_name FROM students s "
	// + " JOIN s.cities c WHERE lower(s.student_last_name) = lower(?1)")
	// @Query("SELECT s FROM students s WHERE lower(s.student_last_name) =
	// lower(?1)")
	// public List<Student> findByLastnameOrFirstname(String student_last_name);
	public List<Student> findByLastName(String lastName);

	/**
	 * Finds Students by using the student Id List.
	 * @param ids
	 * @return A list of students which student id is exactly match with given student id.
	 *  If no student is found, this method returns an empty list.
	 */
	public List<Student> findBystudentIdIn(List<Integer> ids);
	
	/**
	 * Finds Students by using the student's date of birth.
	 * @param studentDOB
	 * @return A list of students which Date of birth is before the given date.
	 *  If no student is found, this method returns an empty list.
	 */
	public List<Student> findBystudentDOBBefore(Date studentDOB);
	
	/**
	 * Finds Students by using the student's date of birth and first name.
	 * @param studentDOB
	 * @param firstName
	 * @return A list of students which Date of birth is before the given date and first name is exactly match.
	 *  If no student is found, this method returns an empty list.
	 */
	public List<Student> findBystudentDOBBeforeAndFirstName(Date studentDOB, String firstName);

	/**
	 * Finds Students by using the city id as a search criteria.
	 * @param city_name
	 * @return A list of students which city's id is an exact match with the given
	 *         city id. If no student is found, this method returns an empty list.
	 */
	// @Query("SELECT s FROM students s JOIN s.cities c WHERE lower(c.city_id) =
	// lower(?1)")
	@Query("SELECT s FROM students s JOIN s.cities c WHERE lower(c.cityId) = lower(?1)")
	public List<Student> findStudentsByCityId(int city_id);
	
	/*
	 * finds students by using fatherName, lastName and firstName as a search criteria
	 * @param fatherName
	 * @param lastName
	 * @param firstName
	 * @return A list of students whose father's,last's and first's Names exactly matches. 
	 * If no student is found, this method returns an empty list.
	 */
	@Query("select s from students s where (?1 is null or ?1='' or lower(s.fatherName) = lower(?1)) and (?2 is null or ?2='' or lower(s.lastName) = lower(?2)) and (?3 is null or ?3='' or lower(s.firstName) = lower(?3))")
	public List<Student> search(String fatherName, String lastName, String firstName);

}
