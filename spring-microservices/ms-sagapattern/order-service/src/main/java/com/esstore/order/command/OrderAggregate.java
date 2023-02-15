package com.esstore.order.command;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import com.esstore.order.event.OrderCreatedEvent;

@Aggregate
public class OrderAggregate {

	@AggregateIdentifier
	private  String orderId;
	private  String userId;
	private  String productId;
	private  int quantity;
	private  String addressId;
	private  String orderStatus;

	// Default Constructor will be used by Axon framework
	public OrderAggregate() {
		
	}
	@CommandHandler
	public OrderAggregate(CreateOrderCommand createOrderCommand) throws Exception {
			// this method will be called when we call commandGateway.createCommand()
		
		//Step1 - Validate the request
		if(createOrderCommand.getOrderId().isBlank()) {
			throw new IllegalArgumentException("Order id can not be blank");
		}
		OrderCreatedEvent event = new OrderCreatedEvent();
		BeanUtils.copyProperties(createOrderCommand, event);
		//Aggregate it using AggregateLifeCycle class
		/*what apply does is
		 *1. it will dispatch the event to all the event handlers inside this aggregate as such we don't have any event handler
		 * 
		 */
		AggregateLifecycle.apply(event);
		/*
		 * if(true) { throw new Exception("throwing error explicitly"); }
		 */
		}
	
	@EventSourcingHandler
	public void on(OrderCreatedEvent orderCreatedEvent) {
		
		this.orderId = orderCreatedEvent.getOrderId();
		this.userId = orderCreatedEvent.getUserId();
		this.productId = orderCreatedEvent.getProductId();
		this.addressId = orderCreatedEvent.getAddressId();
		this.orderStatus = orderCreatedEvent.getOrderStatus();
		this.quantity = orderCreatedEvent.getQuantity();
		
	}
	
}
