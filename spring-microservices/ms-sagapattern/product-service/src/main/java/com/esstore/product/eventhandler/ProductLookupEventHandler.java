package com.esstore.product.eventhandler;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esstore.product.event.ProductCreatedEvent;
import com.esstore.product.persistent.ProductLookup;
import com.esstore.product.repo.ProductLookupRepo;

@Component
@ProcessingGroup("product-group")
public class ProductLookupEventHandler {

	@Autowired
	private 	ProductLookupRepo productLookupRepo;
	
	/*Method name could be anything*/
	@EventHandler
	public void onXyz(ProductCreatedEvent productCreatedEvent) {
		
		ProductLookup pLookup = new ProductLookup(null, productCreatedEvent.getProductId(), productCreatedEvent.getProductName());
		productLookupRepo.save(pLookup);
	}
}
