package com.ecommerse.microservices.order.service;

import com.ecommerse.microservices.order.client.InventoryClient;
import com.ecommerse.microservices.order.dto.OrderRequest;
import com.ecommerse.microservices.order.model.Order;
import com.ecommerse.microservices.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public OrderService(OrderRepository orderRepository, InventoryClient inventoryClient) {
        this.orderRepository = orderRepository;
        this.inventoryClient = inventoryClient;
    }

    public void placeOrder(OrderRequest orderRequest) {

        boolean isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());

        if(isProductInStock) {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setSkuCode(orderRequest.skuCode());
            order.setPrice(orderRequest.price());
            order.setQuantity(orderRequest.quantity());

            orderRepository.save(order);
        } else {
            throw new RuntimeException("Product is not in stock" + orderRequest.skuCode());
        }
    }
}
