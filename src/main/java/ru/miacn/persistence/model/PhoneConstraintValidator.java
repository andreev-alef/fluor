package ru.miacn.persistence.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneConstraintValidator implements ConstraintValidator<PhoneAnnotation, String> {

	@Override
	public void initialize(PhoneAnnotation constraintAnnotation) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		boolean hasErrors = false;
		FacesContext fc = FacesContext.getCurrentInstance();
		Pattern p;
		Matcher m;
		
		p = Pattern.compile("[\\d\\s\\(\\)\\-\\+]+", Pattern.UNICODE_CHARACTER_CLASS);
		m = p.matcher(value);
		if (!m.matches()){
			hasErrors = true;
			fc.addMessage(null, getErrorMessage("Введены недопустимые символы в номере телефона."));
		}

		p = Pattern.compile("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{5,10}$", Pattern.UNICODE_CHARACTER_CLASS);
		m = p.matcher(value);
		if (!m.matches()){
			hasErrors = true;
			fc.addMessage(null, getErrorMessage("Некорректный номер телефона."));
		}

		return !hasErrors;
	}

	private FacesMessage getErrorMessage(String text) {
		FacesMessage msg = new FacesMessage(text);
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		return msg;
	}
}
