package match.controllers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import match.beans.User;
import match.beans.repos.UserRepo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = match.Application.class)
public class SuperAdminIntTest {

	@Autowired
	private UserRepo userRepository;

	@Before
	public void setup() {
	}

	@Test
	public void testSuperAdminInstalledByDefault() {
		User findOne = userRepository.findOne(1l);
		assertEquals(findOne.getUsername(), "superadmin");
	}

}
