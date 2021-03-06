package security.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@PasswordMatch(groups = PasswordValidationGroup.class)
@Data
@Entity
@NamedQuery(name="LoginUser.findAll", query="SELECT u FROM LoginUser u")
public class LoginUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Size(min=3, max=64, message="Enter a username that contains {min} to {max} characters")
	@Column(length=64, unique=true, nullable=false)
	private String username;

	@Column(nullable=false)
	private String password;

	@Transient
	@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$",
		message="Password value must contain at least 8 characters with at least 1 uppercase letter, 1 lowercase letter, and 1 number",
		groups = {PasswordValidationGroup.class})
	private String plainTextPassword;

	@Transient
	@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$",
		message="Confirm Password value must contain at least 8 characters with at least 1 uppercase letter, 1 lowercase letter, and 1 number",
		groups = {PasswordValidationGroup.class})
	private String confirmedPlainTextPassword;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="LoginUserGroup", 
		joinColumns={
			@JoinColumn(name="userid")
		}, 
		inverseJoinColumns={
			@JoinColumn(name="groupid")
		}
	)
	private List<LoginGroup> groups = new ArrayList<>();

}