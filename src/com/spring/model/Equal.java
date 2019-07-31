package com.spring.model;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy={EqualValidator.class})
@Target(value={java.lang.annotation.ElementType.METHOD,java.lang.annotation.ElementType.FIELD,java.lang.annotation.ElementType.ANNOTATION_TYPE,java.lang.annotation.ElementType.CONSTRUCTOR,java.lang.annotation.ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Equal {
	String message()default "与指定字符串不相等";
	Class<?>[]groups() default {};
	Class<? extends Payload >[] payload() default {};
	String value();
}
