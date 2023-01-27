package com.esstore.product.eventhandler;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esstore.product.event.ProductCreatedEvent;
import com.esstore.product.persistent.Product;
import com.esstore.product.repo.ProductRepository;

@Component
@ProcessingGroup("product-group")
public class ProductEventHandler {
/*For this processor group axon framework will create a new separate new Tracking processor, this tracking
 * processor will use a special tracking token to avoid multiple processing of the same event in different thread.
 * if we do not specify @ProcessingGroup("product-event") axon framework will by default assign the group which name will be your class package name.
 * */
	@Autowired
	ProductRepository productEventRepository;
	
	@EventHandler
	public void on(ProductCreatedEvent event) throws Exception {
		Product product = new Product();
		BeanUtils.copyProperties(event, product);
		productEventRepository.save(product);
		//throw new Exception("throwing exception explicitly");
	}
	
	/* It will handle the exception which is thrown from same class*/
	@ExceptionHandler(resultType = Exception.class)
	public void handleError(Exception ex) throws Exception {
		/*
		 * propagating exception, in this case transaction will be rolled back by
		 * default, no entries will be made in event store or database.
		 * This will happen only if your processing group is configured to use subscribing event processor
		 * Note- Default behavior of axon framework is to handle the exception, log it and continue the execution.
		 */
		throw ex;
	}
}
