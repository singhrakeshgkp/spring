package com.esstore.product;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

import com.esstore.product.command.CommandInterceptor;
import com.esstore.product.exception.ProductServiceEventHandlerException;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	@Autowired
	public void registerCommandInterceptor(ApplicationContext context, CommandBus bus) {
		bus.registerDispatchInterceptor(context.getBean(CommandInterceptor.class));
	}
	
  @Autowired
  public void registerException(EventProcessingConfigurer configurer) {
	  configurer.registerListenerInvocationErrorHandler("product-group", conf-> new ProductServiceEventHandlerException());
	  // we can reuse existing error class which is PropagatingErrorHandler sice we have created our own so commented below
	  //configurer.registerListenerInvocationErrorHandler("product-group", conf-> PropagatingErrorHandler.instance());
  }
}
