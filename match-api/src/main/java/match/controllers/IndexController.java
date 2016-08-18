package match.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController  {

	@RequestMapping(method = RequestMethod.GET, path = "/")
	public void get(HttpServletResponse response) {
		try {
			response.sendRedirect("app/index.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
