package com.esstore.order.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esstore.order.persistent.OrderLookup;

@Repository
public interface OrderLookupRepo  extends JpaRepository<OrderLookup, Long>{
	
	OrderLookup findByOrderId(String orderId);

}
