package match;

import static java.time.ZoneId.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import match.beans.User;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MatchApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testLocalDateTime() {
		LocalDateTime ldt = LocalDateTime.now();

		User user = new User();
		user.setCreatedDate(ldt);

		assertThat(ldt, is(user.getCreatedDate()));
	}

	public static void main(String[] args) {
		LocalDateTime ldt = LocalDateTime.now();
		Date date = Date.from(ldt.atZone(systemDefault()).toInstant());

		System.out.println(ldt);
		System.out.println(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(date));
	}

}
