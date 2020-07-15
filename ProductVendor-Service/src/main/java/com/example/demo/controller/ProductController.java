package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;

@RestController
public class ProductController {
	@Autowired
	ProductService productService;
	
	


	@PostMapping(value = "/addproduct", consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Product addProduct(@RequestBody @Valid Product product) {
		return productService.save(product);
	}
	
	@GetMapping(value="/getproducts")
	public List<Product> getAll(){
		return productService.getAll();
	}	
	
	@GetMapping(value="/getbyid/{id}")
	public Product getById(@PathVariable Long id){
		return productService.getById(id);
	}

	@DeleteMapping(value="/deletebyid/{id}")
	public void deleteById(@PathVariable Long id){
		productService.del(id);
	}

}
