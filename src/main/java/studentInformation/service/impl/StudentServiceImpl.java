package studentInformation.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import studentInformation.dao.SearchCriteria;
import studentInformation.dao.StudentDAO;
import studentInformation.dao.StudentRepository;
import studentInformation.dto.StudentDTO;
import studentInformation.exception.CityNotFoundException;
import studentInformation.exception.StudentNotFoundException;
import studentInformation.model.Student;
import studentInformation.service.StudentService;

@Service(value = "studentService")
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private StudentDAO studentRepositoryDAO;
	
	@Autowired
	private CityServiceImpl cityService;
	
	private final String NoStudentFoundException = "No student found.";
	private final String NoStudentFoundLastNameException = "No student found for this last name : ";
	private final String StudentRetreivalException = "There is an error in reteriving Student's information. Kindly verify the data from Database or Database connection.";
	private final String NoCityFoundWithSpecifiedIdException = "No city exists with the provided Id.";
	private final String NoStudentFoundForCityException = "No student found for the city";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<StudentDTO> findAll() {
		List<StudentDTO> students = new ArrayList<>();
		try {
			for (Student student : studentRepository.findAll()) {
				StudentDTO stu = new StudentDTO();
				stu.setStudentId(student.getStudent_id());
				stu.setStudentFatherName(student.getStudent_father_name());
				stu.setStudentFirstName(student.getStudent_first_name());
				stu.setStudentLastName(student.getStudent_last_name());
				stu.setStudentDOB(student.getStudent_dob());
				//stu.setCityName(student.getCity().getCity_name());
				stu.setCityName(student.getCity() == null ? "N/A" : student.getCity().getCity_name());
				students.add(stu);
			}
		} catch (Exception e) {
			throw e;
		}
		if (students.isEmpty())
			throw new StudentNotFoundException(NoStudentFoundException);
		return students;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public StudentDTO findById(int id) throws Exception {
		StudentDTO stuDTO = new StudentDTO();

		try {
			Student currStudent = studentRepository.findOne(id);
			stuDTO.setStudentId(currStudent.getStudent_id());
			stuDTO.setStudentFirstName(currStudent.getStudent_first_name());
			stuDTO.setStudentLastName(currStudent.getStudent_last_name());
			stuDTO.setStudentFatherName(currStudent.getStudent_father_name());
			stuDTO.setStudentDOB(currStudent.getStudent_dob());
			stuDTO.setCityId(currStudent.getCity() == null ? 0 : currStudent.getCity().getCity_id());
			stuDTO.setCityName(currStudent.getCity() == null ? "" : currStudent.getCity().getCity_name());
		} catch (Exception e) {
			throw new StudentNotFoundException(StudentRetreivalException);
		}
		return stuDTO;
	}

	/**
	 * {@inheritDoc}
	 * @throws Exception 
	 */
	@Override
	public Student save(Student student) throws Exception {
		String strEmptyException = "";
		if(student.getStudent_father_name() == "" || student.getStudent_father_name() == null)
			strEmptyException = "Father Name could not be Empty.";
		if(student.getStudent_first_name() == "" || student.getStudent_first_name() == null)
			strEmptyException += "First Name could not be Empty.";
		if(student.getStudent_last_name() == "" || student.getStudent_last_name() == null)
			strEmptyException += "Last Name could not be Empty.";
		if(strEmptyException != "")
			throw new Exception(strEmptyException);
		return studentRepository.save(student);
	}
	
	/*@Override
	public void save(Student student) {
		try {
			studentRepository.save(student);
		} catch (Exception e) {
			throw e;
		}
	}*/

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(int id) {
		try {
			studentRepository.delete(id);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Student> findByLastName(String lastName) {
		List<Student> lsStudents = new ArrayList<>();
		try {
			lsStudents = studentRepository.findByLastName(lastName);
		} catch (Exception e) {
			throw e;
		}
		if (lsStudents.isEmpty())
			throw new StudentNotFoundException(NoStudentFoundLastNameException + lastName);
		return lsStudents;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Student> findStudentsByCityId(int cityId) {
		List<Student> lsStudents = new ArrayList<>();
		try {
			lsStudents = studentRepository.findStudentsByCityId(cityId);
		} catch (Exception e) {
			throw e;
		}
		if (lsStudents.isEmpty()) {
			String cityName;
			try {
				cityName = cityService.findById(cityId).getCity_name();
			} catch (Exception cityException) {
				throw new CityNotFoundException(NoCityFoundWithSpecifiedIdException);
			}
			throw new StudentNotFoundException(NoStudentFoundForCityException + " : " + cityName);
		}
		return lsStudents;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Student> getAll() {
		List<Student> students = new ArrayList<>();
		try {
			for (Student student : studentRepository.findAll()) {
				students.add(student);
			}
		} catch (Exception e) {
			throw e;
		}
		return students;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<StudentDTO> findByStudentIds(List<Integer> ids) {
		List<StudentDTO> students = new ArrayList<>();
		try {
			for (Student student : studentRepository.findBystudentIdIn(ids)) {
				StudentDTO stu = new StudentDTO();
				stu.setStudentId(student.getStudent_id());
				stu.setStudentFatherName(student.getStudent_father_name());
				stu.setStudentFirstName(student.getStudent_first_name());
				stu.setStudentLastName(student.getStudent_last_name());
				stu.setStudentDOB(student.getStudent_dob());
				stu.setCityId(student.getCity() == null ? 0 : student.getCity().getCity_id());
				stu.setCityName(student.getCity() == null ? "N/A" : student.getCity().getCity_name());
				students.add(stu);
			}
		} catch (Exception e) {
			throw e;
		}
		return students;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<StudentDTO> findByStudentDOBBefore(Date dateOfBirth) {
		List<StudentDTO> students = new ArrayList<>();
		try {
			for (Student student : studentRepository.findBystudentDOBBefore(dateOfBirth)) {
				StudentDTO stu = new StudentDTO();
				stu.setStudentId(student.getStudent_id());
				stu.setStudentFatherName(student.getStudent_father_name());
				stu.setStudentFirstName(student.getStudent_first_name());
				stu.setStudentLastName(student.getStudent_last_name());
				stu.setStudentDOB(student.getStudent_dob());
				stu.setCityId(student.getCity() == null ? 0 : student.getCity().getCity_id());
				stu.setCityName(student.getCity() == null ? "N/A" : student.getCity().getCity_name());
				students.add(stu);
			}
		} catch (Exception e) {
			throw e;
		}
		return students;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<StudentDTO> findByStudentDateOfBirthBeforeAndFirstName(Date studentDOB, String firstName) {
		List<StudentDTO> students = new ArrayList<>();
		try {
			for (Student student : studentRepository.findBystudentDOBBeforeAndFirstName(studentDOB, firstName)) {
				StudentDTO stu = new StudentDTO();
				stu.setStudentId(student.getStudent_id());
				stu.setStudentFatherName(student.getStudent_father_name());
				stu.setStudentFirstName(student.getStudent_first_name());
				stu.setStudentLastName(student.getStudent_last_name());
				stu.setStudentDOB(student.getStudent_dob());				
				stu.setCityId(student.getCity() == null ? 0 : student.getCity().getCity_id());
				stu.setCityName(student.getCity() == null ? "N/A" : student.getCity().getCity_name());
				students.add(stu);
			}
		} catch (Exception e) {
			throw e;
		}
		return students;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<StudentDTO> searchStudentsByParams(String fatherName, String lastName, String firstName) {
		List<StudentDTO> students = new ArrayList<>();
		try {
			for (Student student : studentRepository.search(fatherName, lastName, firstName)) {
				StudentDTO stu = new StudentDTO();
				stu.setStudentId(student.getStudent_id());
				stu.setStudentFatherName(student.getStudent_father_name());
				stu.setStudentFirstName(student.getStudent_first_name());
				stu.setStudentLastName(student.getStudent_last_name());
				stu.setStudentDOB(student.getStudent_dob());				
				stu.setCityId(student.getCity() == null ? 0 : student.getCity().getCity_id());
				stu.setCityName(student.getCity() == null ? "N/A" : student.getCity().getCity_name());
				students.add(stu);
			}
		} catch (Exception e) {
			throw e;
		}
		return students;
	}

	@Override
	public List<StudentDTO> searchStudentsByCriteria(List<SearchCriteria> searchQuery) {
		List<StudentDTO> students = new ArrayList<>();
		try {
			for (Student student : studentRepositoryDAO.searchStudent(searchQuery)) {
				StudentDTO stu = new StudentDTO();
				stu.setStudentId(student.getStudent_id());
				stu.setStudentFatherName(student.getStudent_father_name());
				stu.setStudentFirstName(student.getStudent_first_name());
				stu.setStudentLastName(student.getStudent_last_name());
				stu.setStudentDOB(student.getStudent_dob());				
				stu.setCityId(student.getCity() == null ? 0 : student.getCity().getCity_id());
				stu.setCityName(student.getCity() == null ? "N/A" : student.getCity().getCity_name());
				students.add(stu);
			}
		} catch (Exception e) {
			throw e;
		}
		return students;
	}

	@Override
	public List<StudentDTO> searchStudentsBySpecification(Specification<Student> specs) {
		List<StudentDTO> students = new ArrayList<>();
		try {
			for (Student student : studentRepository.findAll(specs)) {
				StudentDTO stu = new StudentDTO();
				stu.setStudentId(student.getStudent_id());
				stu.setStudentFatherName(student.getStudent_father_name());
				stu.setStudentFirstName(student.getStudent_first_name());
				stu.setStudentLastName(student.getStudent_last_name());
				stu.setStudentDOB(student.getStudent_dob());				
				stu.setCityId(student.getCity() == null ? 0 : student.getCity().getCity_id());
				stu.setCityName(student.getCity() == null ? "N/A" : student.getCity().getCity_name());
				students.add(stu);
			}
		} catch (Exception e) {
			throw e;
		}
		return students;
	}
}
