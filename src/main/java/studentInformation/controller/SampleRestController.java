package studentInformation.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import studentInformation.dao.SearchCriteria;
import studentInformation.dao.StudentSpecificationBuilder;
import studentInformation.dto.StudentDTO;
import studentInformation.model.City;
import studentInformation.model.Student;
import studentInformation.service.impl.CityServiceImpl;
import studentInformation.service.impl.StudentServiceImpl;
import studentInformation.util.Util;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/students")
public class SampleRestController {

	@Autowired
	private StudentServiceImpl studentService;

	@Autowired
	private CityServiceImpl cityService;

	@Autowired
	public SampleRestController() {
	};

	@GetMapping("/hello")
	public String hello() {
		return "HELLO World!!!";
	}

	// @GetMapping("/")
	// public String allStudents() {
	// try {
	// return studentService.getAll().toString();
	// } catch (Exception e) {
	// throw e;
	// }
	// }

	@GetMapping("/")
	public ResponseEntity<List<StudentDTO>> listAllStudents() throws Exception {
		List<StudentDTO> students = new ArrayList<StudentDTO>();
		try {
			students = studentService.findAll();
		} catch (Exception e) {
			throw e;
		}
		try {
			return new ResponseEntity<List<StudentDTO>>(students, HttpStatus.OK);
		} catch (Exception e) {
			throw e;
		}
	}

	@GetMapping("/{id}")
	public StudentDTO GetStudentById(@PathVariable int id) throws Exception {
		StudentDTO stuDTO = new StudentDTO();

		try {
			stuDTO = studentService.findById(id);
		} catch (Exception e) {
			throw e;
		}

		return stuDTO;
	}

	// @GetMapping("/{id}")
	// public String GetStudentById(@PathVariable int id) throws Exception {
	// try {
	// return studentService.findById(id).toString();
	// } catch (Exception e) {
	// throw new Exception(studentNotFound);
	// }
	// }

	@GetMapping("/ByLastName/{lastName}")
	public List<StudentDTO> GetStudentByLastName(@PathVariable String lastName) throws Exception {
		List<StudentDTO> stuList = new ArrayList<StudentDTO>();
		List<Integer> lsIds = new ArrayList<Integer>();
		try {
			for (Student student : studentService.findByLastName(lastName)) {
				StudentDTO stuDTO = new StudentDTO();
				stuDTO.setStudentId(student.getStudent_id());
				stuDTO.setStudentFirstName(student.getStudent_first_name());
				stuDTO.setStudentLastName(student.getStudent_last_name());
				stuDTO.setStudentFatherName(student.getStudent_father_name());
				stuDTO.setStudentDOB(student.getStudent_dob());
				stuDTO.setCityId(student.getCity() == null ? 0 : student.getCity().getCity_id());
				stuDTO.setCityName(student.getCity() == null ? "N/A" : student.getCity().getCity_name());
				stuList.add(stuDTO);
				lsIds.add(student.getStudent_id());
			}
		} catch (Exception e) {
			throw e;
		}

		List<StudentDTO> studentList = studentService.findByStudentIds(lsIds);
		studentList = studentService.findByStudentDOBBefore(stuList.get(0).getStudentDOB());
		studentList = studentService.findByStudentDateOfBirthBeforeAndFirstName(stuList.get(0).getStudentDOB(),
				stuList.get(0).getStudentFirstName());
		return stuList;
	}

	@GetMapping("/city/{id}")
	public List<StudentDTO> GetStudentsByCityId(@PathVariable int id) throws Exception {
		List<StudentDTO> stuList = new ArrayList<StudentDTO>();
		try {
			for (Student student : studentService.findStudentsByCityId(id)) {
				StudentDTO stuDTO = new StudentDTO();
				stuDTO.setStudentId(student.getStudent_id());
				stuDTO.setStudentFirstName(student.getStudent_first_name());
				stuDTO.setStudentLastName(student.getStudent_last_name());
				stuDTO.setStudentFatherName(student.getStudent_father_name());
				stuDTO.setStudentDOB(student.getStudent_dob());
				stuDTO.setCityId(student.getCity().getCity_id());
				stuDTO.setCityName(student.getCity() == null ? "N/A" : student.getCity().getCity_name());
				stuList.add(stuDTO);
			}

		} catch (Exception e) {
			throw e;
		}
		return stuList;
	}

	@PostMapping("/add")
	public StudentDTO createStudent(@RequestBody StudentDTO stu) throws Exception {
		Student stuObject = new Student();
		stuObject.setStudent_first_name(stu.getStudentFirstName());
		stuObject.setStudent_last_name(stu.getStudentLastName());
		stuObject.setStudent_father_name(stu.getStudentFatherName());
		stuObject.setStudent_dob(stu.getStudentDOB());
		City studentCity = cityService.findById(stu.getCityId());
		stuObject.setCity(studentCity);
		Student savedStudent;
		try {
			savedStudent = studentService.save(stuObject);
		} catch (Exception e) {
			throw e;
		}
		if (savedStudent != null)
			stu.setStudentId(savedStudent.getStudent_id());
		return stu;
	}

	/*
	 * @PostMapping("/add") public String createStudent(@RequestBody Student
	 * stuObject) { try { studentService.save(stuObject); } catch (Exception e) {
	 * throw e; } return "Record added successfully!!!"; }
	 */

	@PostMapping("update/{id}")
	public StudentDTO updateStudent(@PathVariable int id, @RequestBody StudentDTO student) throws Exception {
		Student currStudent = new Student();
		StudentDTO currStudentDTO = new StudentDTO();
		try {
			currStudentDTO = studentService.findById(id);
		} catch (Exception e) {
			throw e;
		}
		currStudent = Util.convertDTOToObject(currStudentDTO);
		currStudent.setStudent_first_name(student.getStudentFirstName());
		currStudent.setStudent_last_name(student.getStudentLastName());
		currStudent.setStudent_father_name(student.getStudentFatherName());
		currStudent.setStudent_dob(student.getStudentDOB());
		currStudent.setCity(cityService.findById(student.getCityId()));
		try {
			studentService.save(currStudent);
		} catch (Exception e) {
			throw e;
		}
		return student;
	}

	// @PostMapping("/{id}")
	// public String updateStudent(@PathVariable int id, @RequestBody Student
	// student) throws Exception {
	// Student currStudent = new Student();
	// try {
	// currStudent = studentService.findById(id);
	// } catch (Exception e) {
	// throw e;
	// }
	// /*
	// * if (currStudent == null) {
	// * logger.error("Unable to update. User with id {} not found.", id); return
	// new
	// * ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id +
	// * " not found."), HttpStatus.NOT_FOUND); }
	// */
	// currStudent.setStudent_first_name(student.getStudent_first_name());
	// currStudent.setStudent_last_name(student.getStudent_last_name());
	// currStudent.setStudent_father_name(student.getStudent_father_name());
	// currStudent.setStudent_dob(student.getStudent_dob());
	// // currStudent.setCity(student.getCity());
	// try {
	// studentService.save(currStudent);
	// } catch (Exception e) {
	// throw e;
	// }
	// return "Record updated successfully!!!";
	// }

	@DeleteMapping("/delete/{id}")
	public StudentDTO deleteStudent(@PathVariable int id) throws Exception {
		StudentDTO stuDTO = new StudentDTO();
		try {
			studentService.delete(id);
		} catch (Exception e) {
			throw e;
		}
		stuDTO.setStudentId(id);
		return stuDTO;
	}

	// @DeleteMapping("/delete/{id}")
	// public String deleteStudent(@PathVariable int id) {
	// try {
	// studentService.delete(id);
	// } catch (Exception e) {
	// throw e;
	// }
	// return "Record deleted successfully!!!";
	// }
	
	@GetMapping("/search/")
	public List<StudentDTO> searchStudent(@RequestParam(value = "fatherName") String fatherName,
			@RequestParam(value = "lastName", required = false) String lastName,
			@RequestParam(value = "firstName", required = false) String firstName) throws Exception {
		List<StudentDTO> students = new ArrayList<StudentDTO>();
		try {
			students = studentService.searchStudentsByParams(fatherName, lastName, firstName);
		} catch (Exception e) {
			throw e;
		}
		try {
			return students;
		} catch (Exception e) {
			throw e;
		}
	}
	
	@GetMapping("/searchCriteria/")
	public List<StudentDTO> searchStudentByCriteria(@RequestParam(value = "search", required = false) String search) throws Exception {
		List<StudentDTO> students = new ArrayList<StudentDTO>();
		List<SearchCriteria> params = new ArrayList<SearchCriteria>();
		
//		if (search != null) {
//            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
//            Matcher matcher = pattern.matcher(search + ",");
//            while (matcher.find()) {
//                params.add(new SearchCriteria(matcher.group(1), 
//                  matcher.group(2), matcher.group(3)));
//            }
//        }
		
		StudentSpecificationBuilder builder = new StudentSpecificationBuilder(params);
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
         
        Specification<Student> spec = builder.build();
		
        try {
			students = studentService.searchStudentsBySpecification(spec);
		} catch (Exception e) {
			throw e;
		}
        
//		try {
//			students = studentService.searchStudentsByCriteria(params);
//		} catch (Exception e) {
//			throw e;
//		}
//		try {
//			return students;
//		} catch (Exception e) {
//			throw e;
//		}
		return students;
	}
}
