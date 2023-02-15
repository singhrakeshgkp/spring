package com.esstore.order.controller;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esstore.order.command.CreateOrderCommand;
import com.esstore.order.persistent.Order;

@RestController
@RequestMapping("/api/orders")
public class OrderCommandController {

	private final CommandGateway commandGateway;
	
	
	  @Autowired 
	  public OrderCommandController(CommandGateway commandGateway){ 
	  this.commandGateway = commandGateway; 
	  }
	 
	@PostMapping
	private ResponseEntity<String> createOrder(@RequestBody Order order){
		CreateOrderCommand command = CreateOrderCommand.builder()
									    .orderId(UUID.randomUUID().toString())
									    .addressId(order.getAddressId())
									    .quantity(order.getQuantity())
									    .productId(order.getProductId())
									    .orderStatus(order.getOrderStatus())
									    .build();
									  
									   
		String status = commandGateway.sendAndWait(command);
		return new ResponseEntity<String>(status,HttpStatus.CREATED);
		/*
		 * try { return new ResponseEntity<String>(status,HttpStatus.CREATED);
		 * }catch(Exception ex) { return new
		 * ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR); }
		 */
	}
}
