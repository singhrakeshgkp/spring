package com.proudct.route;

import java.time.LocalDateTime;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


//@Component
public class ProductTimerRouter extends RouteBuilder{

	@Autowired
	SystemTime systemTime;
	
	@Autowired
	LoggingMsg loggingMsg;
	
	
	@Override
	public void configure() throws Exception {

	 /*
	   * We perform mainly two operation
	   * 1. Transformation
	   * 2. Processing
	   * */
		
		//Endpoint-> timer:product-timer
		/* Step- 1 start
		 * By default it will get null body and timer:product-timer endpoint
		 * and push the same on log:product-timer endpoint each second
		 * */
		//from("timer:product-timer")
		//.to("log:product-timer");
		
		/*Step 1 end*/
		
		/*Step 2 start. Transformation of message Produce some message and consumer that to another endpoint*/
		/*
		from("timer:product-timer")
		.log("${body}")  // print null
		.transform().constant("test data")
		.log("${body}")//print test data
		.bean(systemTime)//in case of single method no need to specify method name
		.log("${body}")// print current time
		.to("log:product-timer");
		*/
	   /*Step 2 End*/
		
	/* Step 3- start */
		/*
		from("timer:product-timer")
		.log("${body}")  // print null
		.transform().constant("test data")
		.log("${body}")//print test data
		.bean(systemTime)//Transformation operation using bean,in case of single method no need to specify method name
		.log("${body}")// print current time final Transformation
		.bean(loggingMsg)//Processing operation with bean -> msg will be printed same as in final transformation
		.to("log:product-timer");
		*/
		/*Step 3 - End*/
		
		/* Step 4-  start */
		
		from("timer:product-timer")
		.log("${body}")  // print null
		.transform().constant("test data")
		.log("${body}")//print test data
		.bean(systemTime)//Transformation operation using bean,in case of single method no need to specify method name
		.log("${body}")// print current time final Transformation
		.bean(loggingMsg)//Processing operation with bean -> msg will be printed same as in final transformation
		.process(new TestProcessor()) // processing operation using processor
		.to("log:product-timer");
		
		/* Step 4-  end */
	}

	
}

@Component
class SystemTime{
	public String getTime() {
		return "time is "+LocalDateTime.now();
	}
}

@Component 
@Slf4j
class LoggingMsg{
	public void log(String msg) {
		log.info("Logging Msg {}",msg);
	}
}


class TestProcessor implements Processor{

	private final Logger  logger = LoggerFactory.getLogger(TestProcessor.class);
	@Override
	public void process(Exchange exchange) throws Exception {

		logger.info("TestProcessor.process() message is {}",exchange.getMessage().getBody());
	}
	
}
