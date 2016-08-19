package match.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import match.beans.Applicant;
import match.beans.QApplicant;
import match.beans.User;
import match.beans.User.Role;
import match.beans.repos.ApplicantRepo;

@RestController
@RequestMapping("/api/applicants")
@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
public class ApplicantController extends CrudController<Applicant, ApplicantRepo> {

	@Getter
	@Autowired
	private ApplicantRepo repository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public ResponseEntity<Applicant> get(@PathVariable("id") Long id) {
		return super.get(id);
	}

	@Override
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody @Valid Applicant applicant) {
		User user = applicant.getUser();
		user.setRole(Role.ROLE_APPLICANT);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return super.save(applicant);
	}

	@Override
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody @Valid Applicant applicant) {
		return super.update(applicant);
	}

	@Override
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Applicant>> query(Predicate predicate, @PageableDefault(size = 15) Pageable pageable) {
		return super.query(QApplicant.applicant.user.role.eq(Role.ROLE_APPLICANT).and(predicate), pageable);
	}
	
	@Override
	@RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		return super.delete(id);
	}
}
