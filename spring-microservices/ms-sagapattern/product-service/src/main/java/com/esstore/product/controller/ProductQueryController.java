package com.esstore.product.controller;

import java.util.List;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esstore.product.model.ProductRestResModel;
import com.esstore.product.query.ProductQuery;

@RestController
@RequestMapping("/api/products")
public class ProductQueryController {
	
	private final QueryGateway queryGateway;
	
	@Autowired
	public ProductQueryController(QueryGateway queryGateway) {
		this.queryGateway = queryGateway;
	}
	
	@GetMapping
	public List<ProductRestResModel> getProducts(){
		ProductQuery productQuery = new ProductQuery();
		List<ProductRestResModel> products = queryGateway.query(productQuery, ResponseTypes.multipleInstancesOf(ProductRestResModel.class)).join();
		return products;
	}

}
