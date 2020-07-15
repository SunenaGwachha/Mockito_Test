package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Product;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.exception.VendorNotFoundException;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product save(Product product) {
		// TODO Auto-generated method stub
		return productRepository.save(product);
	}

	@Override
	public Product getById(Long id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id).orElseThrow(()->new ProductNotFoundException(id));
	}

	@Override
	public List<Product> getAll() {
		// TODO Auto-generated method stub


		List<Product> result= productRepository.findAll();

		System.out.println(result);


		return result;
	}

	@Override
	public List<Product> getByIdVendor(Long id) {
		// TODO Auto-generated method stub
		return productRepository.findProducts(id);
	}

	@Override
	public void del(Long id) {
		productRepository.deleteById(id);
	}

}
