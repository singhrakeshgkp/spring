package com.esstore.product.advice;

import java.util.Date;

import javax.persistence.EntityExistsException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.esstore.product.exception.ProductErrorResponse;

@ControllerAdvice
public class ProductServiceErrorHandlerAdvice {

	@ExceptionHandler(value = {EntityExistsException.class})
	public ResponseEntity<Object> entityExitException(EntityExistsException ex, WebRequest webRequest){
		//Step-1 Return single line error
		/*
		 * return new ResponseEntity<>(ex.getMessage(),new
		 * HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
		 */
		//Step-2 return custom error obj with additional data such as error code, msg time stamp.
		ProductErrorResponse errorResponse = new ProductErrorResponse("product-ms-001", ex.getMessage(), new Date());
		return new ResponseEntity<>(errorResponse,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> errorHandler(Exception ex, WebRequest webRequest){
		return new ResponseEntity<>(ex.getMessage(),new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
