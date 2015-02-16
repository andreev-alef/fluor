package ru.miacn.persistence.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneConstraintValidator implements ConstraintValidator<PhoneAnnotation, String> {
	@Override
	public void initialize(PhoneAnnotation constraintAnnotation) {

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		boolean hasErrors = false;
		Pattern p;
		Matcher m;
		
		if (!value.isEmpty()){
			p = Pattern.compile("[\\d\\s\\(\\)\\-\\+]+", Pattern.UNICODE_CHARACTER_CLASS);
			m = p.matcher(value);
			if (!m.matches()){
				hasErrors = true;
				context.disableDefaultConstraintViolation();
	            context.buildConstraintViolationWithTemplate("{PhoneAnnotation.illegalCharError}").addConstraintViolation();
			}

			p = Pattern.compile("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{5,10}$", Pattern.UNICODE_CHARACTER_CLASS);
			m = p.matcher(value);
			if (!m.matches()){
				hasErrors = true;
				context.disableDefaultConstraintViolation();
	            context.buildConstraintViolationWithTemplate("{PhoneAnnotation.error}").addConstraintViolation();
			}
			
		}

		return !hasErrors;
	}
}
