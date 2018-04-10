package studentInformation.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;

import studentInformation.dao.SearchCriteria;
import studentInformation.dto.StudentDTO;
import studentInformation.model.Student;

public interface StudentService {

	/*
	 * Find all the students.
	 * @param None
	 * @Return List of all the student's DTOs.
	 */
	public List<StudentDTO> findAll();
	
	/*
	 * Find all the students.
	 * @param None
	 * @Return List of all the student's objects.
	 */
	public List<Student> getAll();
	
	/*
	 * Find the student that matches the provided id.
	 * @param student Id
	 * @Return student's DTO.
	 */
	public StudentDTO findById(int id) throws Exception;
	
//	public void save(Student student);
	
	/*
	 * Save the student object
	 * @param Student Object
	 * @Return student's object.
	 */
	public Student save(Student student) throws Exception;
	
	/*
	 * Delete the student that matches the provided id.
	 * @param student Id
	 * @Return None.
	 */
	public void delete(int id);
	
	/**
	 * Finds Students by using the last name as a search criteria.
	 * @param lastName
	 * @return A list of students whose last name is an exact match with the given
	 *         last name. If no student is found, this method returns an empty list.
	 */
	public List<Student> findByLastName(String lastName);
	/**
	 * Finds Students by using city id.
	 * @param cityId
	 * @return A list of students whose city id is an exact match with the given
	 *         city id. If no student is found, this method returns an empty list.
	 */
	public List<Student> findStudentsByCityId(int cityId);
	
	/**
	 * Finds Students by using the student Id List.
	 * @param ids
	 * @return A list of students which student id is exactly match with given student id.
	 *  If no student is found, this method returns an empty list.
	 */
	public List<StudentDTO> findByStudentIds(List<Integer> ids);
	
	/**
	 * Finds Students by using the student's date of birth.
	 * @param studentDOB
	 * @return A list of students which Date of birth is before the given date.
	 *  If no student is found, this method returns an empty list.
	 */
	public List<StudentDTO> findByStudentDOBBefore(Date dateOfBirth);
	
	/**
	 * Finds Students by using the student's date of birth and first name.
	 * @param studentDOB
	 * @param firstName
	 * @return A list of students which Date of birth is before the given date and first name is exactly match.
	 *  If no student is found, this method returns an empty list.
	 */
	public List<StudentDTO> findByStudentDateOfBirthBeforeAndFirstName(Date studentDOB, String firstName);
	
	/*
	 * finds students by using fatherName, lastName and firstName as a search criteria
	 * @param fatherName
	 * @param lastName
	 * @param firstName
	 * @return A list of students whose father's,last's and first's Names exactly matches. 
	 * If no student is found, this method returns an empty list.
	 */
	public List<StudentDTO> searchStudentsByParams(String fatherName, String lastName, String firstName);
	
	/*
	 * finds students by using search criteria
	 * @param searchQuery
	 * @return A list of students those matches with the criteria. 
	 * If no student is found, this method returns an empty list.
	 */
	public List<StudentDTO> searchStudentsByCriteria(List<SearchCriteria> searchQuery);
	
	/*
	 * finds students by using search criteria
	 * @param specs
	 * @return A list of students those matches with the criteria. 
	 * If no student is found, this method returns an empty list.
	 */
	public List<StudentDTO> searchStudentsBySpecification(Specification<Student> specs);
	
}
