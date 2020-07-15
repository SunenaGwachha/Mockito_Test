package com.example.demo.controller;

import java.util.List;

import javax.print.attribute.standard.Media;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;

@RestController
public class ProductController {

	public static final String APPLICATION_JSON_UTF8_VALUE = "application/json;charset=UTF-8";
	@Autowired
	ProductService productService;
	
	


	@PostMapping(value = "/addproduct", produces=APPLICATION_JSON_UTF8_VALUE, consumes = APPLICATION_JSON_UTF8_VALUE)
	public Product addProduct(@RequestBody @Valid Product product) {
		return productService.save(product);
	}
	
	@GetMapping(value="/getproducts")
	public List<Product> getAll(){
		return productService.getAll();
	}	
	
	@GetMapping(value="/getbyid/{id}",produces= APPLICATION_JSON_UTF8_VALUE)
	//@GetMapping(value = "/getbyid/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Product getById(@PathVariable Long id){
		return productService.getById(id);
	}

	@DeleteMapping(value="/deletebyid/{id}")
	public void deleteById(@PathVariable Long id){
		productService.del(id);
	}

}
