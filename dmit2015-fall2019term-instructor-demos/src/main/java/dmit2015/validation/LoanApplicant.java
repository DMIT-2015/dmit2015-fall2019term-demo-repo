package dmit2015.validation;

import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class LoanApplicant {

	@NotBlank(message = "First Name value is required")
	@Size(min = 1, message = "First Name must contain at least ${min} characters")
	private String firstName;
	
	@NotBlank(message = "Last Name value is required")
	@Size(min = 2, message = "Last Name must contain at least ${min} characters")
	private String lastName;
	
	@PastOrPresent(message = "Date of Birth must be in the past")
	private LocalDate birthDate;
	
	@Email(message = "A valid email address is required")
	private String email;
	
	@Pattern(regexp = "((\\(\\d{3}\\) ?)|(\\d{3}-))?\\d{3}-\\d{4}", 
			message = "A valid phone number is required")
	private String phone;
	
	@DecimalMin(value = "5000", message = "Amount must be at least ${formatter.format('$%,.2f',value)}")
	private double amount;

}
