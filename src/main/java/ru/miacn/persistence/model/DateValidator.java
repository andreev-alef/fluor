package ru.miacn.persistence.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateValidator implements ConstraintValidator<MinDate, Date> {
	private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
	private Date minDate;

	@Override
	public void initialize(MinDate ca) {
		try {
			minDate = sdf.parse(ca.min());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isValid(Date value, ConstraintValidatorContext cvc) {
		if (value != null) {
			try {
				Date newDate = sdf.parse(sdf.format(value));
				return !newDate.before(minDate);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}
		return true;
	}
}
