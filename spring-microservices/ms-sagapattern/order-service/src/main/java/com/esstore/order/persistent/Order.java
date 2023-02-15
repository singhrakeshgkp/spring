package com.esstore.order.persistent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name = "orders")
@Data
public class Order implements Serializable  {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4798331211768685320L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
	public  String id;
	
	@Column(name="order_id")
	private String orderId;

	@Column(name="user_id")
	private  String userId;
	
	@Column(name = "product_id")
	private  String productId;
	
	@Column(name="quantity")
	private  int quantity;
	
	@Column(name="address_id")
	private  String addressId;
	
	//@Enumerated(EnumType.STRING)
	@Column(name = "order_status")
	private  String orderStatus;

}
