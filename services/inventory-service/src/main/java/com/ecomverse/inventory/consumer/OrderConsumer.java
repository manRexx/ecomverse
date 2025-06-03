package com.ecomverse.inventory.consumer;

import com.ecomverse.inventory.dto.OrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderConsumer {

    @RabbitListener(queues = {"${spring.rabbitmq.queue.name}"})
    public void consumeOrder(OrderEvent orderEvent) {
        log.info("Order event received: {}", orderEvent);
        // Add your inventory update logic here
    }
}
