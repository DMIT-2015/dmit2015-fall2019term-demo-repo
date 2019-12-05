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
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.Data;
import security.entity.LoginUser;

@XmlRootElement	// for automatic mapping from JAXB annotated class to XML and JSON 
@Data
@Entity
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
	
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(shape=Shape.STRING, pattern="yyyy-MM-dd")
	@Column(nullable = false)
	@NotNull(message = "Date is required")
	@PastOrPresent(message = "Date cannot be in the future")
	private LocalDate date = LocalDate.now();
	
	@NotNull(message = "User is required")
	@ManyToOne
	@JoinColumn(name = "user_id")
	private LoginUser user;
}
