package match.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Applicant extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -7696991411016302499L;

	@NotNull
	@OneToOne
	@JoinColumn(name = "USER_ID")
	private User user;
}
