package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Vendor;

public interface VendorService {
	
	public Vendor save(Vendor vendor);
	public Vendor getById(Long id);
	public List<Vendor> getAll();

}
