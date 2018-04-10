package studentInformation.controller.test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@EnableSpringDataWebSupport
@ActiveProfiles("test")
public abstract class BaseControllerTest {

@Autowired 
protected MockMvc mvc;




}
