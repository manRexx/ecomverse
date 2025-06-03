package com.ecomverse.inventory.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryResponse {
    private String productId;
    private boolean inStock;
    private int quantity;
}
