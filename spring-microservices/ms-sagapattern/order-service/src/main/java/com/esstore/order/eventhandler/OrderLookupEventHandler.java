package com.esstore.order.eventhandler;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esstore.order.event.OrderCreatedEvent;
import com.esstore.order.persistent.OrderLookup;
import com.esstore.order.repo.OrderLookupRepo;

@Component
@ProcessingGroup("order-group")
public class OrderLookupEventHandler {

	@Autowired
	private OrderLookupRepo orderLookupRepo;
	
	/*Method name could be anything*/
	@EventHandler
	public void onXyz(OrderCreatedEvent orderCreatedEvent) {
		
		OrderLookup oLookup = new OrderLookup(null, orderCreatedEvent.getOrderId());
		orderLookupRepo.save(oLookup);
	}
}
