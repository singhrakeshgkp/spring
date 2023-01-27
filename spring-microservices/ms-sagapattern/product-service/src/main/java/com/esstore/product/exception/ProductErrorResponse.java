package com.esstore.product.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductErrorResponse {

	private String errorCode;
	private String message;
	private Date timeStamp;
	
}
