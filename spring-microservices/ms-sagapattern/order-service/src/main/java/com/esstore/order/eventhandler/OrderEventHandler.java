package com.esstore.order.eventhandler;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esstore.order.event.OrderCreatedEvent;
import com.esstore.order.persistent.Order;
import com.esstore.order.repo.OrderRepository;

@Component
@ProcessingGroup("order-group")
public class OrderEventHandler {
	@Autowired
	OrderRepository orderRepository;
	
	@EventHandler
	public void on(OrderCreatedEvent orderCreatedEvent) throws Exception {
		Order order = new Order();
		BeanUtils.copyProperties(orderCreatedEvent, order);
		orderRepository.save(order);
		//throw new Exception("throwing exception explicitly");
	}
	
	/* It will handle the exception which is thrown from same class*/
	@ExceptionHandler(resultType = Exception.class)
	public void handleError(Exception ex) throws Exception {
		/*
		 * propagating exception, in this case transaction will be rolled back by
		 * default, no entries will be made in event store or database.
		 * This will happen only if your processing group is configured to use subscribing event processor
		 * Note- Default behavior of axon framework is to handle the exception, log it and continue the execution.
		 */
		throw ex;
	}
}
