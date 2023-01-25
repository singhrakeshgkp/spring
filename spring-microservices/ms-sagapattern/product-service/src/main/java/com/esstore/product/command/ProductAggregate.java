package com.esstore.product.command;

import java.math.BigDecimal;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import com.esstore.product.event.ProductCreatedEvent;

@Aggregate
public class ProductAggregate {

	@AggregateIdentifier
	private  String uniqueId;
	private  String productName;
	private  BigDecimal price;
	private  int quantity;

	// Default Constructor will be used by Axon framework
	public ProductAggregate() {
		
	}
	@CommandHandler
	public ProductAggregate(CreateProductCommand createProductCommand) {
			// this method will be called when we call commandGateway.createCommand()
		
		//Step1 - Validate the request
		if(createProductCommand.getProductName().isBlank()) {
			throw new IllegalArgumentException("Product name can not be blank");
		}
		ProductCreatedEvent event = new ProductCreatedEvent();
		BeanUtils.copyProperties(createProductCommand, event);
		//Aggregate it using AggregateLifeCycle class
		/*what apply does is
		 *1. it will dispatch the event to all the event handlers inside this aggregate as such we don't have any event handler
		 * 
		 */
		AggregateLifecycle.apply(event);
		}
	
	@EventSourcingHandler
	public void on(ProductCreatedEvent event) {
		
		this.uniqueId = event.getUniqueId();
		this.productName = event.getProductName();
		this.price = event.getPrice();
		
	}
	
}
