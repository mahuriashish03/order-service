package com.ecommerse.microservices.order.repository;

import com.ecommerse.microservices.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
