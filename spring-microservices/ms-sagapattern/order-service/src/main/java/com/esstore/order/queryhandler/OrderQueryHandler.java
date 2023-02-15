package com.esstore.order.queryhandler;

import java.util.ArrayList;
import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esstore.order.model.OrderRestResModel;
import com.esstore.order.query.OrderQuery;
import com.esstore.order.repo.OrderRepository;

@Component
public class OrderQueryHandler {

	@Autowired
	private OrderRepository orderRepository;
	
	@QueryHandler
	public List<OrderRestResModel> findProducts(OrderQuery orderQuery){
		
		List<OrderRestResModel> orders = new ArrayList<>();
		orderRepository.findAll().forEach(entity->{
			OrderRestResModel orderObj = new OrderRestResModel();
			BeanUtils.copyProperties(entity, orderObj);
			orders.add(orderObj);
		});
		
	  return orders;
	}
}
