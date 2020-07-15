package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Vendor;
import com.example.demo.service.VendorService;

@RestController
@CrossOrigin
public class VendorController {
	
	
	@Autowired
	VendorService vendorService;
	
	@PostMapping(value="/addvendor")
	public Vendor addVendor(@RequestBody @Valid Vendor vendor){
		return vendorService.save(vendor);
	}
	
	@GetMapping(value="/getvendors")
	public ResponseEntity<List<Vendor>> getAll(){
		List<Vendor> list=vendorService.getAll();
		if(!list.isEmpty())
		return new ResponseEntity<>(list,HttpStatus.OK);
		else
		return new ResponseEntity<>(list,HttpStatus.NOT_FOUND);
	}
	
	
	@GetMapping(value="/vendorbyid/{id}")
	public ResponseEntity<?> findById(@Valid @PathVariable(value="id") Long id) {
		return new ResponseEntity<>(vendorService.getById(id),HttpStatus.OK);
		
	}
	
	@GetMapping(value="/public")
	public ResponseEntity<?> publicView(){
		return new ResponseEntity<>("{\"message\" : \"This page is for all public\"}",HttpStatus.OK);
	}
	
	

}
