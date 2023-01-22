package com.springtestcontainer.config;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.StringJoiner;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Component
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ApplicationInterceptor  implements MethodInterceptor{

	
	private final String tokenKey= "uniquetoken";

	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		String methodName = methodInvocation.getMethod().getName();
		HttpServletRequest request = 
		        ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes())
		                .getRequest();
		String headerValue = request.getHeader("unique-key");
		StringJoiner logMsg = new StringJoiner(" ");
		logMsg.add("methodName=")
		.add(methodName)
		.add(" , ")
		.add("unique-key=")
		.add(headerValue);
		var start = Instant.now();
	    var response = methodInvocation.proceed();
	    ChronoUnit unit = ChronoUnit.MILLIS;
	    var end = Instant.now();
	    var duration = String.format("%s %s", unit.between(end, start),unit.name().toString());
		MDC.put("logMsg", logMsg.toString());
		return response;
	}
	
	//OncePerRequestFilter- code for OncePerRequestFilter interceptor class
	/*
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		try {
			final String token;
			if(request.getHeader("tokenKey")!=null && !request.getHeader(tokenKey).isEmpty()) {
				token = request.getHeader(tokenKey);
			}else {
				token = UUID.randomUUID().toString();
			}
			MDC.put(tokenKey, token);
		}catch(Exception ex) {
			
		}finally {
			MDC.remove(tokenKey);
		}
	}
	*/

}
