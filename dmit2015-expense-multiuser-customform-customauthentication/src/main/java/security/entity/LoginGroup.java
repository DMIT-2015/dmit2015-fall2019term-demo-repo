package security.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import lombok.Data;

import java.util.List;

@XmlRootElement(name = "LoginGroup")
@Data
@Entity
@NamedQuery(name="LoginGroup.findAll", query="SELECT g FROM LoginGroup g")
@XmlAccessorType(XmlAccessType.FIELD)
public class LoginGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@NotBlank(message="Enter a group name")
	@Column(length=64, unique=true, nullable=false)
	private String groupname;

	@XmlTransient
	@ManyToMany(mappedBy="groups")
	private List<LoginUser> users;	

}