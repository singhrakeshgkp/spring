package com.esstore.order.command;

import java.util.List;
import java.util.function.BiFunction;

import javax.persistence.EntityExistsException;

import org.axonframework.messaging.Message;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esstore.order.persistent.OrderLookup;
import com.esstore.order.repo.OrderLookupRepo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CommandInterceptor implements MessageDispatchInterceptor<Message<?>>{

	@Autowired
	private OrderLookupRepo repo;
	@Override
	public BiFunction<Integer, Message<?>, Message<?>> handle(List<? extends Message<?>> messages) {
		return ((index,command)->{
			log.info("Processing {} command",command.getPayloadType());
			if(CreateOrderCommand.class.equals(command.getPayloadType())) {
				//command specific logic can be written here such as validtion, etc
				CreateOrderCommand createOrderCommand =(CreateOrderCommand) command.getPayload();
				log.info("Inside command specific logic block");
				OrderLookup oLookup = repo.findByOrderId(createOrderCommand.getOrderId());
				if(oLookup != null)
					throw new EntityExistsException("Record with given Order id is already exist");
			}
			return command;
		});
	}

}
