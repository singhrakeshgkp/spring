package com.esstore.order.model;

import lombok.Data;

@Data
public class OrderRestResModel {

	private  String orderId;
	private  String userId;
	private  String productId;
	private  int quantity;
	private  String addressId;
	private  String orderStatus;

}
