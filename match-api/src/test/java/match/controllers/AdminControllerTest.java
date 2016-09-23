package match.controllers;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import match.beans.User;
import match.beans.repos.UserRepo;
import match.util.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {match.Application.class})
public class AdminControllerTest {
	
	@Mock
	private UserRepo mockedUserRepo;

	@Mock
	private PasswordEncoder mockedPasswordEncoder;

	private MockMvc mockedMockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		doReturn("encoded_password").when(mockedPasswordEncoder).encode(anyString());
		this.mockedMockMvc = MockMvcBuilders.standaloneSetup(new AdminController(mockedUserRepo, mockedPasswordEncoder))
				.build();
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
}
