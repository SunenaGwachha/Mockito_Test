package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
	
	@Query("SELECT p FROM Product p WHERE p.vendor.id = :id")
	List<Product> findProducts(@Param("id") Long id);

}
