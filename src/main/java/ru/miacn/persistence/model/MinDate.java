package ru.miacn.persistence.model;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Target ({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = DateValidator.class )
public @interface MinDate {
	String message () default "{DateAnnotation.Before}";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
	
	/**
	 * String date in 'dd.MM.yyyy' format
	 * default "01.01.1900"
	 */
	String min() default "01.01.1900";
}
