package ru.miacn.persistence.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FioConstraintValidator implements ConstraintValidator<FIOAnnotation, String> {

	@Override
	public void initialize(FIOAnnotation ca) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext cvc) {
		boolean hasErrors = false;
		FacesContext fc = FacesContext.getCurrentInstance();
		Pattern p;
		Matcher m;
		
		p = Pattern.compile("[а-яА-ЯёЁ-]+", Pattern.UNICODE_CHARACTER_CLASS);
		m = p.matcher(value);
		if (!m.matches()){
			hasErrors = true;
			fc.addMessage(null, getErrorMessage("В поле \"ФИО\" : "+(value)+" введены недопустимые символы."));
		}

		p = Pattern.compile("(\\ \\ |\\ \\-|\\ Ьь|\\ Ъъ)", Pattern.UNICODE_CHARACTER_CLASS);
		m = p.matcher(value);
		if (m.find()){
			hasErrors = true;
			fc.addMessage(null, getErrorMessage("В поле \"ФИО\" : "+(value)+" введены недопустимые символы после пробела."));
		}

		p = Pattern.compile("(\\-\\ |\\-\\-|\\-Ьь|\\-Ъъ)", Pattern.UNICODE_CHARACTER_CLASS);
		m = p.matcher(value);
		if (m.find()){
			hasErrors = true;
			fc.addMessage(null, getErrorMessage("В поле \"ФИО\" : "+(value)+" введены недопустимые символы после дефиса."));
		}
		p = Pattern.compile("^[а-яА-ЯёЁ&&[^ЬьЪъ]]", Pattern.UNICODE_CHARACTER_CLASS);
		m = p.matcher(value);
		if (!m.find()){
			hasErrors = true;
			fc.addMessage(null, getErrorMessage("В поле \"ФИО\" : "+(value)+" введен недопустимый первый символ."));
		}
		
		p = Pattern.compile("(\\ |\\-)$", Pattern.UNICODE_CHARACTER_CLASS);
		m = p.matcher(value);
		if (m.find()){
			hasErrors = true;
			fc.addMessage(null, getErrorMessage("В поле \"ФИО\" : "+(value)+" введен недопустимый последний символ."));
		}
		
		p = Pattern.compile("[УЕЫАОЭЯИЮЁуеыаоэяиюё]", Pattern.UNICODE_CHARACTER_CLASS);
		m = p.matcher(value);
		if (!m.find()){
			hasErrors = true;
			fc.addMessage(null, getErrorMessage("Поле \"ФИО\" : "+(value)+" должно содержать хотя бы одну гласную букву."));
		}
		return !hasErrors;
	}

	private FacesMessage getErrorMessage(String text) {
		FacesMessage msg = new FacesMessage(text);
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		return msg;
	}
}
