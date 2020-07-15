package com.example.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.demo.domain.Vendor;

public class VendorValidator implements ConstraintValidator<VendorValidate,Vendor> {

	@Override
	public boolean isValid(Vendor value, ConstraintValidatorContext context) {
		
		return true;//value.getAddress().length()>value.getPhone().length();
	}

}
