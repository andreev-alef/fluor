package ru.miacn;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class ApplicationBean {
	private Locale locale;
	private SimpleDateFormat dateFormat;

	public ApplicationBean() {
		setLocale(Locale.getDefault());
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
		setDateFormat(((SimpleDateFormat) DateFormat.getDateInstance(DateFormat.MEDIUM, locale)));
	}

	public SimpleDateFormat getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(SimpleDateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}
}
