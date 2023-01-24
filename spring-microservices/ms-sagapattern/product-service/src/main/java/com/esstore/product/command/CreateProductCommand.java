package com.esstore.product.command;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateProductCommand {
	private final String uniqueId;
	private final String productName;
	private final BigDecimal price;
	private final int quantity;
}
