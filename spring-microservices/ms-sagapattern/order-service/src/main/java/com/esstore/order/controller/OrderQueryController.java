package com.esstore.order.controller;

import java.util.List;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esstore.order.model.OrderRestResModel;
import com.esstore.order.query.OrderQuery;

@RestController
@RequestMapping("/api/orders")
public class OrderQueryController {
	
	private final QueryGateway queryGateway;
	
	@Autowired
	public OrderQueryController(QueryGateway queryGateway) {
		this.queryGateway = queryGateway;
	}
	
	@GetMapping
	public List<OrderRestResModel> getProducts(){
		OrderQuery orderQuery = new OrderQuery();
		List<OrderRestResModel> orders = queryGateway.query(orderQuery, ResponseTypes.multipleInstancesOf(OrderRestResModel.class)).join();
		return orders;
	}

}
