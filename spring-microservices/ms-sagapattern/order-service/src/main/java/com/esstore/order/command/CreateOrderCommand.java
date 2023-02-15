package com.esstore.order.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateOrderCommand {
	
	@TargetAggregateIdentifier
	private  String orderId;
	private  String userId;
	private  String productId;
	private  int quantity;
	private  String addressId;
	private  String orderStatus;
}
