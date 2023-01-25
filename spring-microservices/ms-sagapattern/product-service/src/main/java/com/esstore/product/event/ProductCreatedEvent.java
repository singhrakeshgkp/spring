package com.esstore.product.event;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductCreatedEvent {

   
   
	private  String uniqueId;
	private  String productName;
	private  BigDecimal price;
	private  int quantity;

}
