package expense.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

import lombok.Data;
import security.entity.LoginUser;

@Data
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Expense implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
		
	@Column(nullable = false)
	@NotBlank(message = "Description value is required")
	private String description;
	
	@Column(nullable = false)
	@NotNull(message = "Amount value is required")
	@DecimalMin(value = "1.00", message = "Amount must be equal to greater than ${value}")
	private BigDecimal amount;
	
	@Column(nullable = false)
	@NotNull(message = "Date is required")
	@PastOrPresent(message = "Date cannot be in the future")
	private LocalDate date = LocalDate.now();
	
	@NotNull(message = "User is required")
	@ManyToOne
	@JoinColumn(name = "user_id")
	private LoginUser user;
}
