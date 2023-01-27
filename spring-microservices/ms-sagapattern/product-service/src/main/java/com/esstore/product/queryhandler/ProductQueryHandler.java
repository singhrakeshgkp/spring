package com.esstore.product.queryhandler;

import java.util.ArrayList;
import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esstore.product.model.ProductRestResModel;
import com.esstore.product.query.ProductQuery;
import com.esstore.product.repo.ProductRepository;

@Component
public class ProductQueryHandler {

	@Autowired
	private ProductRepository productRepository;
	
	@QueryHandler
	public List<ProductRestResModel> findProducts(ProductQuery productQuery){
		
		List<ProductRestResModel> products = new ArrayList<>();
		productRepository.findAll().forEach(entity->{
			ProductRestResModel productObj = new ProductRestResModel();
			BeanUtils.copyProperties(entity, productObj);
			products.add(productObj);
		});
		
	  return products;
	}
}
