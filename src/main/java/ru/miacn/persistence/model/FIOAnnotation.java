package ru.miacn.persistence.model;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = FioConstraintValidator.class)
@ReportAsSingleViolation
	public @interface FIOAnnotation {
        String message() default "";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
	}
