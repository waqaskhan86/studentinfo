package studentInformation.util;

import studentInformation.dto.StudentDTO;
import studentInformation.model.City;
import studentInformation.model.Student;

public class Util {
	
	public static StudentDTO convertObjectToDTO(Student student) throws Exception {
		StudentDTO stuDTO = new StudentDTO();
		try {
			stuDTO.setStudentId(student.getStudent_id());
			stuDTO.setStudentFirstName(student.getStudent_first_name());
			stuDTO.setStudentLastName(student.getStudent_last_name());
			stuDTO.setStudentFatherName(student.getStudent_father_name());
			stuDTO.setCityId(student.getCity() == null ? 0 : student.getCity().getCity_id());
			stuDTO.setCityName(student.getCity() == null ? "" : student.getCity().getCity_name());
			stuDTO.setStudentDOB(student.getStudent_dob());
			return stuDTO;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static Student convertDTOToObject(StudentDTO studentDTO) throws Exception {
		Student student = new Student();
		City city = new City();
		city.setCity_id(studentDTO.getCityId());
		city.setCity_name(studentDTO.getCityName());
		try {
			student.setStudent_id(studentDTO.getStudentId());
			student.setStudent_dob(studentDTO.getStudentDOB());
			student.setStudent_father_name(studentDTO.getStudentFatherName());
			student.setStudent_first_name(studentDTO.getStudentFirstName());
			student.setStudent_last_name(studentDTO.getStudentLastName());
			student.setCity(city);
			return student;
		} catch (Exception e) {
			throw e;
		}
	}

}
