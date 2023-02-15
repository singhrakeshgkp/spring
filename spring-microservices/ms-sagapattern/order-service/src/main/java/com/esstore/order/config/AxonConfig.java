package com.esstore.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

@Configuration
public class AxonConfig {
	/*
	 * 
	 * 
	 * @Bean public CommandBus commandBus() { SimpleCommandBus commandBus =
	 * SimpleCommandBus.builder().build(); return commandBus;
	 * 
	 * }
	 * 
	 * @Bean public CommandGateway commandGateway() {
	 * 
	 * DefaultCommandGateway defaultCommandGateway =
	 * DefaultCommandGateway.builder().build();
	 * defaultCommandGateway.getCommandBus(). CommandGateway commandGateway =
	 * DefaultCommandGateway.builder().build(); return commandGateway; }
	 */
	 @Bean
	    public XStream xStream() {
	        XStream xStream = new XStream();
	        xStream.addPermission(NoTypePermission.NONE);
	        xStream.addPermission(NullPermission.NULL);
	        xStream.addPermission(PrimitiveTypePermission.PRIMITIVES);
	        xStream.allowTypesByWildcard(new String[] {
	        		"org.axonframework.**",
	                "com.esstore.**"
	        });
	        return xStream;
	    }
	 
		/*
		 * xStream .addPermission(NoTypePermission.NONE); xStream
		 * .addPermission(NullPermission.NULL); xStream
		 * .addPermission(PrimitiveTypePermission.PRIMITIVES);
		 * xStream.allowTypesByWildcard(new String[] { "com.esstore.order.**",
		 * "com.esstore.product.**" });
		 */
}
