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
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import match.beans.User;
import match.beans.repos.UserRepo;
import match.util.TestUtil;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {match.Application.class})
public class AdminControllerIntTest {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Mock
	private UserRepo mockedUserRepo;

	@Mock
	private PasswordEncoder mockedPasswordEncoder;

	private MockMvc mockMvc;
	
	private MockMvc mockedMockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		doReturn("encoded_password").when(mockedPasswordEncoder).encode(anyString());
		this.mockedMockMvc = MockMvcBuilders.standaloneSetup(new AdminController(mockedUserRepo, mockedPasswordEncoder))
				.build();
		this.mockMvc = MockMvcBuilders.standaloneSetup(new AdminController(userRepo, passwordEncoder)).build();
	}

	@Test
	public void testSaveInvalidUser() throws Exception {
		User user = new User();
		when(mockedUserRepo.save(any(User.class))).thenReturn(user);

		this.mockedMockMvc.perform(post("/api/admins")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(TestUtil.toJson(user)))
		.andDo(print())
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void testSaveInvalidUser2() throws Exception {
		User user = new User();
		user.setUsername("ali");
		when(mockedUserRepo.save(any(User.class))).thenReturn(user);

		this.mockedMockMvc.perform(post("/api/admins")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(TestUtil.toJson(user)))
		.andDo(print())
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void testSaveValidUser() throws Exception {
		User user = new User();
		user.setUsername("admin");
		user.setPassword("123456");
		
		when(mockedUserRepo.save(any(User.class))).thenReturn(user);

		this.mockedMockMvc.perform(post("/api/admins")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(TestUtil.toJson(user)))
		.andDo(print())
		.andExpect(status().isOk());
	}
	
	@Test
	public void testSaveDupValidUser() throws Exception {
		User user = new User();
		user.setUsername("testSaveDupValidUser");
		user.setPassword("123456");

		this.mockMvc.perform(post("/api/admins")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(TestUtil.toJson(user)))
		.andDo(print())
		.andExpect(status().isOk());
		
		this.mockMvc.perform(post("/api/admins")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(TestUtil.toJson(user)))
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.message").value("duplicate.user.name"));
	}
	
	@Test
	public void testGetUserDoesntReturnThePassword() throws Exception {
		User user = new User();
		user.setUsername("testGetUserDoesntReturnThePassword");
		user.setPassword("123456");

		this.mockMvc.perform(post("/api/admins")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(TestUtil.toJson(user)))
		.andDo(print())
		.andExpect(status().isOk());
		
		this.mockMvc.perform(get("/api/admins/3")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
		.andDo(print())
		.andExpect(jsonPath("$.password").doesNotExist());
	}
	
	// TODO test user object sent with a different role, test it saved with admin role regardless of the role it sent with
	
	
}
