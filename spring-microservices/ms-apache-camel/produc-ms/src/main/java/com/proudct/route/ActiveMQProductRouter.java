package com.proudct.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQProductRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		from("timer:active-mq-timer?period=10000")//will create the message after each 10 sec
		.transform().constant("active mq test msg")
		.log("${body}")
		.to("activemq:my-activemq-queue");
	}

}
