package com.esstore.product.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.esstore.product.persistent.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>  {
	
	Iterable<Product> findByProductName(String productName);
}
