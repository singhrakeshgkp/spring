package com.estore.core.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReserveProductCommand {

	@TargetAggregateIdentifier // used this annotation so that axon can find the right aggregate instance
	private final String productId;
	private final int quantity;
	private final String orderId;
	private final String userId;
}
