package com.example.demo.exception;

public class ProductNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductNotFoundException(Long id) {

        super(String.format("Entry with Id %d not found", id));
    }

}
