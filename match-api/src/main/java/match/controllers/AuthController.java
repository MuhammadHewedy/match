package match.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import match.beans.User;
import match.util.Util;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@RequestMapping(path = "/loggedInUser", method = RequestMethod.GET)
	public ResponseEntity<User> getLoggedInUser() {
		return ResponseEntity.ok(Util.currentUser().get());
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public ResponseEntity<Void> login() {
		return ResponseEntity.ok().build();
	}
}
