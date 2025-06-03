package com.ecomverse.order.service.impl;

import com.ecomverse.order.dto.OrderRequest;
import com.ecomverse.order.model.Order;
import com.ecomverse.order.model.OrderItem;
import com.ecomverse.order.repository.OrderItemRepository;
import com.ecomverse.order.repository.OrderRepository;
import com.ecomverse.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public Order placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setUserId(orderRequest.getUserId());
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus("PLACED");

        List<OrderItem> orderItems = orderRequest.getOrderItems().stream().map(itemDto -> {
            OrderItem item = new OrderItem();
            item.setProductId(Long.getLong(itemDto.getProductId()));
            item.setQuantity(itemDto.getQuantity());
            item.setPrice(itemDto.getPrice());
            item.setOrder(order);
            return item;
        }).collect(Collectors.toList());

        order.setOrderItems(orderItems);

        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrdersByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }
}
