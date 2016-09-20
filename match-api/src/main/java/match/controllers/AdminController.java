package match.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

import lombok.Getter;
import match.beans.QUser;
import match.beans.User;
import match.beans.User.Role;
import match.beans.repos.UserRepo;
import match.util.Response;

@RestController
@RequestMapping("/api/admins")
@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
public class AdminController extends CrudController<User, UserRepo> {

	@Getter
	private UserRepo repository;
	
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public AdminController(UserRepo userRepo, PasswordEncoder passwordEncoder) {
		this.repository = userRepo;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public ResponseEntity<User> get(@PathVariable("id") Long id) {
		return super.get(id);
	}

	@Override
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> save(@RequestBody @Valid User user) {
		if (repository.exists(QUser.user.username.eq(user.getUsername()))) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Response.of("duplicate.user.name"));
		}
		user.setRole(Role.ROLE_ADMIN);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return super.save(user);
	}

	@Override
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody @Valid User user) {
		return super.update(user);
	}

	@Override
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<User>> query(Predicate predicate, @PageableDefault(size = 15) Pageable pageable) {
		return super.query(QUser.user.role.eq(Role.ROLE_ADMIN).and(predicate), pageable);
	}
}
