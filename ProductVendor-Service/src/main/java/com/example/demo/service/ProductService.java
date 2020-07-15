package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Product;

public interface ProductService {
	Product save(Product product);
	Product getById(Long id);
	List<Product> getAll();
	List<Product> getByIdVendor(Long id);
	void del(Long id);

}
