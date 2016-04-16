package match.beans;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "`user`")
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity implements UserDetails {

	private static final long serialVersionUID = -2019049247658306718L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
	@SequenceGenerator(name = "user_seq_gen", sequenceName = "user_id_seq")
	protected Long id;

	@NotNull
	@Column(updatable = false, unique = true)
	private String username;

	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(updatable = false)
	private String password;

	private boolean enabled = true;

	@Enumerated(EnumType.STRING)
	@Column(updatable = false)
	private Role role;

	@JsonIgnore
	@OneToOne(mappedBy = "user")
	private Applicant applicant;


	@JsonIgnore
	@Override
	public List<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority(role.name()));
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	public static enum Role {
		ROLE_SUPER_ADMIN, ROLE_ADMIN, ROLE_APPLICANT
	}

}
