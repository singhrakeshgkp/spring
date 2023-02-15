package com.esstore.order.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esstore.order.persistent.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>  {
	
	Iterable<Order> findByOrderId(String orderId);
}
