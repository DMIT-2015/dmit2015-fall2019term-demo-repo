package security.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Data;

import java.util.List;

@Data
@Entity
@NamedQuery(name="LoginGroup.findAll", query="SELECT g FROM LoginGroup g")
public class LoginGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@NotBlank(message="Enter a group name")
	@Column(length=64, unique=true, nullable=false)
	private String groupname;

	@ManyToMany(mappedBy="groups")
	private List<LoginUser> users;	

}