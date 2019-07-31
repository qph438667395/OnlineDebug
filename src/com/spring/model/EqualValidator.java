package com.spring.model;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EqualValidator implements ConstraintValidator<Equal, String> {

	String value;
	@Override
	public void initialize(Equal arg0) {
		value=arg0.value();
	}
	@Override
	public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
		if(value.equals(arg0)){
			return true;
		}
		return false;
	}



}
