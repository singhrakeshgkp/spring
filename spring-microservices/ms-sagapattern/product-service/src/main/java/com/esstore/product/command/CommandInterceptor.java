package com.esstore.product.command;

import java.util.List;
import java.util.function.BiFunction;

import javax.persistence.EntityExistsException;

import org.axonframework.messaging.Message;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esstore.product.persistent.ProductLookup;
import com.esstore.product.repo.ProductLookupRepo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CommandInterceptor implements MessageDispatchInterceptor<Message<?>>{

	@Autowired
	private ProductLookupRepo repo;
	@Override
	public BiFunction<Integer, Message<?>, Message<?>> handle(List<? extends Message<?>> messages) {
		return ((index,command)->{
			log.info("Processing {} command",command.getPayloadType());
			if(CreateProductCommand.class.equals(command.getPayloadType())) {
				//command specific logic can be written here such as validtion, etc
				CreateProductCommand createProductCommand =(CreateProductCommand) command.getPayload();
				log.info("Inside command specific logic block");
				ProductLookup pLookup = repo.findByUniqueIdOrProductName(createProductCommand.getUniqueId(),createProductCommand.getProductName());
				if(pLookup != null)
					throw new EntityExistsException("Record with given product name is already exist");
			}
			return command;
		});
	}

}
