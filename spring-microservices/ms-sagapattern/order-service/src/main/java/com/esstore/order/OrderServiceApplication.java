package com.esstore.order;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

import com.esstore.order.command.CommandInterceptor;
import com.esstore.order.config.AxonConfig;
import com.esstore.order.exception.OrderServiceEventHandlerException;

@SpringBootApplication
@EnableDiscoveryClient
@Import({ AxonConfig.class })
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@Autowired
	public void registerCommandInterceptor(ApplicationContext context, CommandBus bus) {
		bus.registerDispatchInterceptor(context.getBean(CommandInterceptor.class));
	}
	
  @Autowired
  public void registerException(EventProcessingConfigurer configurer) {
	  configurer.registerListenerInvocationErrorHandler("order-group", conf-> new OrderServiceEventHandlerException());
	  // we can reuse existing error class which is PropagatingErrorHandler sice we have created our own so commented below
	  //configurer.registerListenerInvocationErrorHandler("product-group", conf-> PropagatingErrorHandler.instance());
  }
  
	
	
	 
}
