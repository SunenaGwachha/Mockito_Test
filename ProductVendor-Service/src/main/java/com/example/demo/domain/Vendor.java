package com.example.demo.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.example.demo.validator.VendorValidate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Vendor")
@VendorValidate(message="{Vendor.length.of.phone.address.check}")
public class Vendor {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@Size(min=4, max=50, message="INVALID PLEASE ENTER ADDRESS BETWEEN 4 AND 50")
	private String address;
	@Pattern(regexp="[\\d]{10}", message = "{phone.validate.number}")
	private String phone;
	@OneToMany(mappedBy="vendor", cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JsonIgnoreProperties("vendor")
	private List<Product> products;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public void addProduct(Product product) {
		this.products.add(product);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Vendor [id=" + id + ", address=" + address + ", phone=" + phone + ", products=" + products + "]";
	}

	public Vendor() {
	}

	public Vendor(Long id, String name, @Size(min = 4, max = 50, message = "INVALID PLEASE ENTER ADDRESS BETWEEN 4 AND 50") String address, @Pattern(regexp = "[\\d]{10}", message = "{phone.validate.number}") String phone) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}
}
