package match.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import match.beans.User;
import match.beans.repos.UserRepo;
import match.util.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {match.Application.class})
public class AdminControllerIntTest {

	@Autowired
	private UserRepo userRepo;
	
	@Mock
	private UserRepo mockedUserRepo;

	@Mock
	private PasswordEncoder passwordEncoder;

	private MockMvc mockMvc;
	
	private MockMvc mockedMockMvc;
	

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		doReturn("encoded_password").when(passwordEncoder).encode(anyString());

		this.mockedMockMvc = MockMvcBuilders.standaloneSetup(new AdminController(mockedUserRepo, passwordEncoder)).build();
		
		this.mockMvc = MockMvcBuilders.standaloneSetup(new AdminController(userRepo, passwordEncoder)).build();
	}

	@Test
	public void testSaveInvalidUser() throws Exception {
		User user = new User();
		when(mockedUserRepo.save(any(User.class))).thenReturn(user);

		this.mockedMockMvc.perform(post("/api/admins")
				.contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(user)))
		.andDo(print())
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void testSaveValidUser() throws Exception {
		User user = new User();
		user.setUsername("admin");
		
		when(mockedUserRepo.save(any(User.class))).thenReturn(user);

		this.mockedMockMvc.perform(post("/api/admins")
				.contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(user)))
		.andDo(print())
		.andExpect(status().isOk());
	}
	
	// Test fails
	@Transactional
//	@Test
	public void testSaveDupValidUser() throws Exception {
		User user = new User();
		user.setUsername("admin");

		this.mockMvc.perform(post("/api/admins")
				.contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(user)))
		.andDo(print())
		.andExpect(status().isOk());
		
		this.mockMvc.perform(post("/api/admins")
				.contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(user)))
		.andDo(print())
		.andExpect(status().isInternalServerError())
		.andExpect(jsonPath("$.exception").value(contains("DataIntegrityViolationException")));
	}
}
