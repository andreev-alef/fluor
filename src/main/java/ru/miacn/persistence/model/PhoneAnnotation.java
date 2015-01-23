package ru.miacn.persistence.model;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.*;

	@Documented
	@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
	@Retention(RUNTIME)
	@Constraint(validatedBy = PhoneConstraintValidator.class)
	@ReportAsSingleViolation
		public @interface PhoneAnnotation {
	        String message() default "";
	        Class<?>[] groups() default {};
	        Class<? extends Payload>[] payload() default {};
		}
