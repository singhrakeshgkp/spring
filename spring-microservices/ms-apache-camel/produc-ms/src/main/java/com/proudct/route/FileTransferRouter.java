package com.proudct.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class FileTransferRouter extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("file:files/input")
		.log("${body}")//will print the file content, not recommended if file size is huge
		.to("file:files/output");
		
	}

}
