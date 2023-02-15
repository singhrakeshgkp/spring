package com.esstore.order.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderErrorResponse {

	private String errorCode;
	private String message;
	private Date timeStamp;
	
}
