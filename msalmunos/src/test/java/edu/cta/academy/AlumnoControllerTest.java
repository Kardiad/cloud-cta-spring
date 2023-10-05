package edu.cta.academy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import edu.cta.academy.common.entity.Alumno;
import edu.cta.academy.util.TestUtil;

@SpringBootTest //con esto lanzamos el servidor en un puerto aleatorio
public class AlumnoControllerTest {
	
	
	@Autowired
	TestRestTemplate template;//para lanzar peticiones

	@Autowired
	private MockMvc mockMvc;
	
	@BeforeAll
	public static void precarga() {
		assertThat(new TestRestTemplate()).hasAllNullFieldsOrProperties();
	}
	
	@Test //con esta anotación, indico que esto es un caso de prueba/test
	@WithMockUser(username = "admin" , password = "zaragoza")
	public void insertarAlumno() throws Exception
	{
		Alumno alumno = new Alumno();
		alumno.setName("JUAN");
		alumno.setSurname("MORENO");
		alumno.setAge(69);
		alumno.setEmail("juan@zgz.es");
		
		String alumno_json = TestUtil.toJSON(alumno);
		
		this.mockMvc.perform(post("/alumno")
				.contentType(MediaType.APPLICATION_JSON)
				.content(alumno_json))
			    .andExpect(status().isCreated())
			    .andExpect(content().contentType(MediaType.APPLICATION_JSON));
		
	}
	
	@Test 
	public void testGetAlumnos()
	{
		Alumno[] arrayAl = this.template.withBasicAuth("admin", "admin123")
				.getForObject("http://localhost:8080/student/list", Alumno[].class);
		List<Alumno> la = Arrays.asList(arrayAl);
		assertThat(la).doesNotContainNull();
	}
	
	@Test							// Indica un caso de prueba
	public void testGetAlumnos_all_ids_must_be_not_zeroes() {
		Alumno[] arrAlumnos = this.template
				.withBasicAuth("admin", "zaragoza")
				.getForObject("http://localhost:8080/alumno", Alumno[].class);
		List<Alumno> la = Arrays.asList(arrAlumnos);
		
		// Aserts
		assertThat(la).allSatisfy(alumno -> assertThat(alumno.getId()).isNotEqualTo(0l));
	}
	
	@Test
	public void cosa() {
		Alumno[] arrayAl = this.template
				.withBasicAuth("admin", "admin123")
				.getForObject("http://localhost:8080/alumno", Alumno[].class);
		List<Alumno> la = Arrays.asList(arrayAl);
		assertThat(la).allMatch(p -> p.getId() != 0);
	}
	
	
}
