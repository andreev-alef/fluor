package ru.miacn.persistence.model;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("dateDeathValidator")
public class DateDeathValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		
		if (value == null) {
			return;
		}
		
		UIInput birthDateComponent = (UIInput) component.getAttributes().get("birthDateComponent");
		
		if (!birthDateComponent.isValid()) {
            return;
        }
		
		Date birthDate = (Date) birthDateComponent.getValue();

        if (birthDate == null) {
            return; 
        }

        Date deathDate = (Date) value;

        if (birthDate.after(deathDate)) {
            throw new ValidatorException(new FacesMessage(
                FacesMessage.SEVERITY_ERROR, "Дата смерти не может быть раньше даты рождения", null));
        }
	}
}
