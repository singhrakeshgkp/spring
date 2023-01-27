package com.esstore.product.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductRestResModel {

	private  String uniqueId;
	private  String productName;
	private  BigDecimal price;
	private  int quantity;

}
