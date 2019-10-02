package dmit2015.validation;

import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.BeforeClass;
import org.junit.Test;

public class LoanApplicantTest {

	private static Validator validator;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	@Test
	public void testSetFirstName() {
		Set<ConstraintViolation<LoanApplicant>> violations = validator.validateValue(
				LoanApplicant.class, "firstName", "");
		assertEquals(2, violations.size());		
	}

}
