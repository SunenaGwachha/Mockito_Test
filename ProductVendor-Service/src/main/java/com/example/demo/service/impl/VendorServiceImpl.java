package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Vendor;
import com.example.demo.exception.VendorNotFoundException;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.VendorService;
@Service
public class VendorServiceImpl implements VendorService {
	@Autowired
	private VendorRepository vendorRepository;

	@Override
	public Vendor save(Vendor vendor) {
		// TODO Auto-generated method stub
		return vendorRepository.save(vendor);
	}

	@Override
	public Vendor getById(Long id) {
		// TODO Auto-generated method stub
		return vendorRepository.findById(id).orElseThrow(()->new VendorNotFoundException(id));
	}

	@Override
	@HystrixCommand(fallbackMethod = "reliable",commandProperties = {
           @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000")})
	

	public List<Vendor> getAll() {
		// TODO Auto-generated method stub
		
		
	 	//for(int i=0;i<=10000000;i++); //testing for time 1000 ms so that it goes to fallBack Method
		
		
		return vendorRepository.findAll();
	}
	
	
	
	
	

	  public List<Vendor> reliable() {
	  return new ArrayList<Vendor>();
	  }


}
