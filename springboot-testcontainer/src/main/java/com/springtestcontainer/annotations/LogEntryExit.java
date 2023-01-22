package com.springtestcontainer.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.temporal.ChronoUnit;

import org.springframework.boot.logging.LogLevel;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogEntryExit {

	LogLevel value() default LogLevel.INFO;
	boolean showResult() default false;
	boolean showExecutionTime() default true;
	ChronoUnit unit() default ChronoUnit.MILLIS;
}
