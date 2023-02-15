package com.esstore.product.command;

import java.math.BigDecimal;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateProductCommand {
	
	@TargetAggregateIdentifier
	private final String productId;
	private final String productName;
	private final BigDecimal price;
	private final int quantity;
}
