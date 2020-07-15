package com.example.demo.exception;

public class VendorNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VendorNotFoundException(Long id) {

        super(String.format("Entry with Id %d not found", id));
    }

}
