package com.esstore.order.saga;

import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.CommandResultMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import com.esstore.order.event.OrderCreatedEvent;
import com.estore.core.command.ReserveProductCommand;
import com.estore.core.events.ProductReservedEvent;

import lombok.extern.slf4j.Slf4j;

@Saga
@Slf4j
public class OrderSagaOrchestration {

	@Autowired
	private transient CommandGateway commandGateway;
	
	@StartSaga
	@SagaEventHandler(associationProperty = "orderId")
	public void handle(OrderCreatedEvent orderCreatedEvent) {
		
		ReserveProductCommand command = ReserveProductCommand.builder()
										.productId(orderCreatedEvent.getProductId())
										.orderId(orderCreatedEvent.getOrderId())
										.userId(orderCreatedEvent.getUserId())
										.quantity(orderCreatedEvent.getQuantity())
   			        					.build();
	    log.info("OrderSagaOrchestration.handle() OrderCreatedEvent order id {}, product Id {}",command.getOrderId(),command.getProductId());
		commandGateway.send(command,new CommandCallback<ReserveProductCommand, Object>() {

			@Override
			public void onResult(CommandMessage<? extends ReserveProductCommand> commandMessage,
					CommandResultMessage<? extends Object> commandResultMessage) {
				
				 if(commandResultMessage.isExceptional()) {
					 log.info("OrderSagaOrchestration.handle(...).new CommandCallback() {...}.onResult() exception occurrred {}",commandResultMessage.optionalExceptionResult().get());
					 // start roll back logic/ compensating transaction
				 }
			}
			
		});
	}
	
	@EndSaga
	@SagaEventHandler(associationProperty = "orderId")
	public void handle(ProductReservedEvent productReservedEvent) {
		
		 log.info("OrderSagaOrchestration.handle() ProductReservedEvent order id {}, product Id {}",productReservedEvent.getOrderId(),productReservedEvent.getProductId());
	}
	
	
	/*
	 * @SagaEventHandler(associationProperty = "paymentId") public void
	 * handle(PaymentProcessedEvent paymentProcessedEvent) {
	 * 
	 * }
	 * 
	 * @EndSaga
	 * 
	 * @SagaEventHandler(associationProperty = "orderId") public void
	 * handle(OrderApprovedEvent orderApprovedEvent) {
	 * 
	 * }
	 */
}
