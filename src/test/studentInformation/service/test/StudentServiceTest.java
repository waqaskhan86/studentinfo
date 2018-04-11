package studentInformation.service.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import studentInformation.dao.StudentRepository;
import studentInformation.model.Student;
import studentInformation.service.StudentServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class StudentServiceTest {
	
	@Mock
	private StudentRepository studentRepositoryMock;	
	
	@InjectMocks
	StudentServiceImpl studentServiceImpl;

	@Test
	public void whenValidIdThenStudentShouldBeFound() throws Exception {
		int id = 1;
	    Student stuObject = new Student("ABC","DEF","JKL", new Date());
	    stuObject.setStudent_id(id);
	    Mockito.when(studentRepositoryMock.findOne(Mockito.anyInt())).thenReturn(stuObject);
	    stuObject = studentServiceImpl.findById(id);
	    assertEquals(stuObject.getStudent_id(),id);
	 }
	
	@Test
	public void studentShouldBeDeleted(){
		int id = 1;
	    doNothing().when(studentRepositoryMock).delete(Mockito.anyInt());
	    studentServiceImpl.delete(id);
	    verify(studentRepositoryMock).delete(id);
	 }
	
	@Test
	public void shouldFindAllStudents() { 
		Mockito.when(studentRepositoryMock.findAll()).thenReturn(new ArrayList<Student>());
		Mockito.doReturn(new ArrayList<Student>()).when(studentRepositoryMock).findAll();
		studentServiceImpl.findAll();
	    verify(studentRepositoryMock).findAll();
		
	}
	
	@Test
	public void shouldSaveStudent() { 
	    Student stuObject = new Student("ABC","DEF","GHI", new Date());
	    Mockito.when(studentRepositoryMock.save(stuObject)).thenReturn(stuObject);
	    studentServiceImpl.save(stuObject);
	    verify(studentRepositoryMock).save(stuObject);
	}	
}
