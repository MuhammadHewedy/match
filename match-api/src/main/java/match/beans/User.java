package match.beans;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity implements UserDetails {

	private static final long serialVersionUID = -2019049247658306718L;

	@NotNull
	@Column(updatable = false, unique = true)
	private String username;

	@NotNull
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	private boolean enabled = true;

	@Enumerated(EnumType.STRING)
	@Column(updatable = false)
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
