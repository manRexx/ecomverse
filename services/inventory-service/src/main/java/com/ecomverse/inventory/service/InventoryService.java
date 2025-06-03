package com.ecomverse.inventory.service;

import com.ecomverse.inventory.dto.InventoryResponse;
import com.ecomverse.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public List<InventoryResponse> checkStock(List<String> productIds) {
        return productIds.stream()
                .map(productId -> inventoryRepository.findByProductId(productId)
                        .map(inventory -> InventoryResponse.builder()
                                .productId(productId)
                                .inStock(inventory.getQuantity() > 0)
                                .quantity(inventory.getQuantity())
                                .build())
                        .orElseGet(() -> InventoryResponse.builder()
                                .productId(productId)
                                .inStock(false)
                                .quantity(0)
                                .build()))
                .collect(Collectors.toList());
    }
}
