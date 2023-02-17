package com.order.router;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class OrderRouter extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("activemq:my-activemq-queue")
		.to("log:received message from active mq")
		.log("${body}");
		
	}

	
}
