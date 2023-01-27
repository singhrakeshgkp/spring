package com.esstore.product.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esstore.product.persistent.ProductLookup;

@Repository
public interface ProductLookupRepo  extends JpaRepository<ProductLookup, Long>{
	
	ProductLookup findByUniqueIdOrProductName(String uniqueId, String productName);

}
