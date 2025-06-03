package com.ecomverse.order.service;

import com.ecomverse.order.dto.OrderRequest;
import com.ecomverse.order.model.Order;

import java.util.List;

public interface OrderService {
    Order placeOrder(OrderRequest orderRequest);
    List<Order> getOrdersByUserId(String userId);
}
