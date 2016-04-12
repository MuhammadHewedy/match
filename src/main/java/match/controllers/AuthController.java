package match.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import match.util.Util;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@RequestMapping(path = "/loggedInUser", method = RequestMethod.GET)
	public ResponseEntity<?> getLoggedInUser() {
		return ResponseEntity.ok(Util.currentUser().get());
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> login() {
		return ResponseEntity.ok(Util.currentUser().get());
	}
}
