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
	
	private String gender;
	private Short age;
	private String nationality;
	private String countryOfPlace;
	private String governorate;
	private String bodyShape;
	private Double weight;
	private Double length;
	private String eyeColor;
	private String hairColor;
	private String skinColor;
	private String secondaryLang;
	private String educationLevel;
	private String job;
	private String religion;
	private String maritalStatus;
	private Boolean haveKids;
	private Boolean likeToHaveKids;
	private Double monthlyIncome;
	private String religionLevel;
	private Boolean hijab;
	private String socialLevel;
	private String about;
	private String partenerCharcs;
	private String phoneNumber;
	private String fatherPhoneNumber;
	private String idImagePath;

	@NotNull
	@OneToOne
	@JoinColumn(name = "USER_ID")
	private User user;
}
