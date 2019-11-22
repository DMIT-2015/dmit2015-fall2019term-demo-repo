package security.entity;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, LoginUser> {

	@Override
	public boolean isValid(LoginUser value, ConstraintValidatorContext context) {
		boolean valid = false;
		if ( StringUtils.isBlank(value.getPlainTextPassword()) || StringUtils.isBlank(value.getConfirmedPlainTextPassword()) ) {
			valid = true;
		} else {
			valid = value.getPlainTextPassword().equals(value.getConfirmedPlainTextPassword());
		}
		return valid;
	}

}
