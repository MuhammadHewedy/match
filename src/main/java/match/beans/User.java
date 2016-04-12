package match.beans;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity implements UserDetails {

	private static final long serialVersionUID = -2019049247658306718L;

	private String username;
	@JsonIgnore
	private String password;
	private boolean enabled = true;
	@Enumerated(EnumType.STRING)
	private Role role;
	@OneToOne(mappedBy = "user")
	private Applicant applicant;

	@Override
	public List<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	public static enum Role {
		ROLE_SUPER_ADMIN, ROLE_ADMIN, ROLE_APPLICANT
	}

}
