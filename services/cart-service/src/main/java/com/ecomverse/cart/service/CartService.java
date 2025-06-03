package com.ecomverse.cart.service;

import com.ecomverse.cart.dto.CartDTO;
import com.ecomverse.cart.dto.CartItemDTO;

public interface CartService {
    CartDTO getCartByUserId(Long userId);
    CartDTO addItemToCart(Long userId, CartItemDTO itemDTO);
    CartDTO updateItemQuantity(Long userId, Long productId, int quantity);
    CartDTO removeItemFromCart(Long userId, Long productId);
    void clearCart(Long userId);
}
