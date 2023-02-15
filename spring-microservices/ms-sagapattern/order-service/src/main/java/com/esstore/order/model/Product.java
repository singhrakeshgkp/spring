package com.esstore.order.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Product {

	private String productName;
	private BigDecimal price;
	private int quantity;
	
}
