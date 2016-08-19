package match.beans.repos;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import match.beans.User;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserRepoTest {

	@Autowired
	private UserRepo userRepo;
	
	@Test
	public void testLocalDateTimeWithNoRepo() {
		String actualUserName = "mhewedy";
		LocalDateTime actualCreatedDate = LocalDateTime.now();

		User user = new User();
		user.setUsername(actualUserName);
		user.setCreatedDate(actualCreatedDate);

		assertThat(actualCreatedDate, is(user.getCreatedDate()));
	}

	@Test
	public void testLocalDateTimeWithRepo() {

		String actualUserName = "mhewedy";
		LocalDateTime actualCreatedDate = LocalDateTime.now();

		User user = new User();
		user.setUsername(actualUserName);
		user.setCreatedDate(actualCreatedDate);

		userRepo.save(user);

		assertThat(actualCreatedDate, is(user.getCreatedDate()));
	}

}
