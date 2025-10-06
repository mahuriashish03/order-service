package com.ecommerse.microservices.order.service;

import com.ecommerse.microservices.order.dto.OrderRequest;
import com.ecommerse.microservices.order.model.Order;
import com.ecommerse.microservices.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void placeOrder(OrderRequest orderRequest) {

        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setSkuCode(orderRequest.skuCode());
        order.setPrice(orderRequest.price());
        order.setQuantity(orderRequest.quantity());

        orderRepository.save(order);

    }
}
