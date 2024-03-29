package com.esstore.product.controller;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esstore.product.command.CreateProductCommand;
import com.esstore.product.model.Product;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/products")
@Slf4j
public class ProductCommandController {

	private final CommandGateway commandGateway;
	
	
	  @Autowired 
	  public ProductCommandController(CommandGateway commandGateway){ 
	  this.commandGateway = commandGateway; 
	  }
	 
	@PostMapping
	private ResponseEntity<String> createProduct(@RequestBody Product product){
		CreateProductCommand command = CreateProductCommand.builder()
									   .productName(product.getProductName())
									   .price(product.getPrice())
									   .quantity(product.getQuantity())
									   .productId(UUID.randomUUID().toString()).build();
		String status = commandGateway.sendAndWait(command);
		return new ResponseEntity<String>(status,HttpStatus.CREATED);
		/*
		 * try { return new ResponseEntity<String>(status,HttpStatus.CREATED);
		 * }catch(Exception ex) { return new
		 * ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR); }
		 */
	}
}
