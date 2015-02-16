package ru.miacn.persistence.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FioConstraintValidator implements ConstraintValidator<FIOAnnotation, String> {

	@Override
	public void initialize(FIOAnnotation ca) {
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext cvc) {
		boolean hasErrors = false;
		Pattern p;
		Matcher m;
		
		if (!value.isEmpty()){
			p = Pattern.compile("[а-яА-ЯёЁ-]+", Pattern.UNICODE_CHARACTER_CLASS);
			m = p.matcher(value);
			if (!m.matches()){
				hasErrors = true;
				cvc.disableDefaultConstraintViolation();
	            cvc.buildConstraintViolationWithTemplate("{FioAnnotation.error}").addConstraintViolation();
			}

			p = Pattern.compile("(\\ \\ |\\ \\-|\\ Ьь|\\ Ъъ)", Pattern.UNICODE_CHARACTER_CLASS);
			m = p.matcher(value);
			if (m.find()){
				hasErrors = true;
				cvc.disableDefaultConstraintViolation();
	            cvc.buildConstraintViolationWithTemplate("{FioAnnotation.illegalCharSpaceError}").addConstraintViolation();
			}

			p = Pattern.compile("(\\-\\ |\\-\\-|\\-Ьь|\\-Ъъ)", Pattern.UNICODE_CHARACTER_CLASS);
			m = p.matcher(value);
			if (m.find()){
				hasErrors = true;
				cvc.disableDefaultConstraintViolation();
	            cvc.buildConstraintViolationWithTemplate("{FioAnnotation.illegalCharDef}").addConstraintViolation();
			}
			p = Pattern.compile("^[а-яА-ЯёЁ&&[^ЬьЪъ]]", Pattern.UNICODE_CHARACTER_CLASS);
			m = p.matcher(value);
			if (!m.find()){
				hasErrors = true;
				cvc.disableDefaultConstraintViolation();
	            cvc.buildConstraintViolationWithTemplate("{FioAnnotation.illegalFirstChar}").addConstraintViolation();
			}
			
			p = Pattern.compile("(\\ |\\-)$", Pattern.UNICODE_CHARACTER_CLASS);
			m = p.matcher(value);
			if (m.find()){
				hasErrors = true;
				cvc.disableDefaultConstraintViolation();
	            cvc.buildConstraintViolationWithTemplate("{FioAnnotation.illegalLastChar}").addConstraintViolation();
			}
			
			p = Pattern.compile("[УЕЫАОЭЯИЮЁуеыаоэяиюё]", Pattern.UNICODE_CHARACTER_CLASS);
			m = p.matcher(value);
			if (!m.find()){
				hasErrors = true;
				cvc.disableDefaultConstraintViolation();
	            cvc.buildConstraintViolationWithTemplate("{FioAnnotation.glasnReq}").addConstraintViolation();
			}
		}
		return !hasErrors;
	}
}
