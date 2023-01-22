package com.springtestcontainer.aspect;

import java.lang.reflect.Method;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.StringJoiner;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Component;

import com.springtestcontainer.annotations.LogEntryExit;

@Aspect
@Component
public class LogEntryExitAspect {

	@Around("@annotation(com.springtestcontainer.annotations.LogEntryExit)")
	public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
		var codeSignature = (CodeSignature)joinPoint.getSignature();
		var methodSignature = (MethodSignature)joinPoint.getSignature();
		Method method = methodSignature.getMethod();
	    Logger logger = LoggerFactory.getLogger(method.getDeclaringClass().getName());
	    var annotation = method.getAnnotation(LogEntryExit.class);
	    ChronoUnit unit = annotation.unit();
	    LogLevel logLevel = annotation.value();
	    boolean showResults = annotation.showResult();
	    boolean showExecutionTime = annotation.showExecutionTime();
	    String methodName = method.getName();
	    String className = codeSignature.getDeclaringTypeName();
	    log(logger,logLevel, entry(className,methodName));
	    var start = Instant.now();
	    var response = joinPoint.proceed();
	    var end = Instant.now();
	    var duration = String.format("%s %s", unit.between(end, start),unit.name().toString());
	    log(logger,logLevel, exit(className,methodName, duration, response, showResults, showExecutionTime));
		return response;
	}
	
	static void log(Logger logger, LogLevel logLevel, String message) {
		switch(logLevel) {
		case DEBUG -> logger.debug(message);
		case TRACE -> logger.trace(message);
		case WARN -> logger.warn(message);
		case ERROR,FATAL -> logger.error(message);
		default -> logger.info(message);
		}
	}
	
	static String entry(String className,String methodName) {
		StringJoiner message = new StringJoiner(" ")
				.add("className=")
				.add(className)
				.add("methodName=")
				.add(methodName)
				.add(" ")
				.add("started");
		return message.toString();
		
	}
	
  static String exit(String className,String methodName, String duration, Object result, boolean showResults,boolean showExecutionTime) {
	  
	  StringJoiner message = new StringJoiner("")
			  .add("className=")
			  .add(className)
			  .add(" methodName=")
			  .add(methodName);
			  //duration/time taken by method
			  

	  if(showExecutionTime) {
		  message.add(" completed in")
		  .add(duration);
	  }if(showResults) {
		  message.add("with return:").add(result.toString());
	  }
			 
	  return message.toString();
  }
	
}
