package studentInformation.controller.test;


import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import studentInformation.controller.SampleRestController;
import studentInformation.model.Student;
import studentInformation.service.StudentServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class StudentRestControllerTest {
	
	private final String BASE_URL = "/student";
	
	@InjectMocks
	SampleRestController mockSampleRestController;
		
	@Mock
	private StudentServiceImpl studentServiceImpl;
	
    private MockMvc mvc;

	
    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.standaloneSetup(mockSampleRestController).build();

    }
	
	@Test
	public void shouldGetAllStudents() throws Exception {
		Mockito.when(studentServiceImpl.findAll()).thenReturn(new ArrayList<Student>());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(BASE_URL+"/")
				.accept(MediaType.APPLICATION_JSON);
		mvc.perform(requestBuilder)
		 .andExpect(status().isOk())
		 .andReturn();
	}
	
	@Test
	public void shouldGetStudentById() throws Exception {
		Student stuObject = new Student("ABC","DEF","PQR", new Date());
		Mockito.when(studentServiceImpl.findById(Mockito.anyInt())).thenReturn(stuObject);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(BASE_URL+"/")
				.param("id", "7")
				.accept(MediaType.APPLICATION_JSON);
		mvc.perform(requestBuilder)
		 .andExpect(status().isOk())
		 .andReturn();
	}
	
	@Test
	public void shouldGetStudentDeleteById() throws Exception {
		doNothing().when(studentServiceImpl).delete(Mockito.anyInt());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(BASE_URL+"/delete/1")
				.accept(MediaType.APPLICATION_JSON);
		mvc.perform(requestBuilder)
		 .andExpect(status().isOk())
		 .andReturn();
        
	}
	
	@Test
	public void shouldCreateStudent() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Student stuObject = new Student();
		stuObject.setStudent_dob(new Date());
		stuObject.setStudent_first_name("ABC");
		stuObject.setStudent_last_name("DEF");
		stuObject.setStudent_father_name("GHI");
		stuObject.setStudent_id(1);
		doNothing().when(studentServiceImpl).save(stuObject);
		String bodyContentJSON = mapper.writeValueAsString(stuObject);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(BASE_URL+"/")
				.content(bodyContentJSON)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		mvc.perform(requestBuilder)
		 .andExpect(status().isOk())
		 .andReturn();
        
	}
	
	@Test
	public void shouldUpdateStudent() throws Exception {
		Student stuObject = new Student();
		stuObject.setStudent_dob(new Date());
		stuObject.setStudent_first_name("ABC");
		stuObject.setStudent_last_name("DEF");
		stuObject.setStudent_father_name("GHI");
		stuObject.setStudent_id(1);
		Mockito.when(studentServiceImpl.findById(Mockito.anyInt())).thenReturn(stuObject);
		doNothing().when(studentServiceImpl).save(stuObject);
		String bodyContentJSON = convertToJson(stuObject);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(BASE_URL+"/1")
				.content(bodyContentJSON)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		mvc.perform(requestBuilder)
		 .andExpect(status().isOk())
		 .andReturn();
        
	}
	
	public static String convertToJson(Object object) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(object);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		return null;
	}

}
