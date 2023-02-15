package com.esstore.order.event;

import lombok.Data;

@Data
public class OrderCreatedEvent {
	
	private String orderId;
	private String productId;
	private String userId;
	private int quantity;
	private String addressId;
	private String orderStatus;

}
