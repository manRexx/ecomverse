package com.ecomverse.cart.controller;

import com.ecomverse.cart.dto.CartDTO;
import com.ecomverse.cart.dto.CartItemDTO;
import com.ecomverse.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<CartDTO> getCart(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCartByUserId(userId));
    }

    @PostMapping("/{userId}/add")
    public ResponseEntity<CartDTO> addItem(
            @PathVariable Long userId,
            @RequestBody CartItemDTO itemDTO) {
        return ResponseEntity.ok(cartService.addItemToCart(userId, itemDTO));
    }

    @PutMapping("/{userId}/update")
    public ResponseEntity<CartDTO> updateItem(
            @PathVariable Long userId,
            @RequestParam Long productId,
            @RequestParam int quantity) {
        return ResponseEntity.ok(cartService.updateItemQuantity(userId, productId, quantity));
    }

    @DeleteMapping("/{userId}/remove")
    public ResponseEntity<CartDTO> removeItem(
            @PathVariable Long userId,
            @RequestParam Long productId) {
        return ResponseEntity.ok(cartService.removeItemFromCart(userId, productId));
    }

    @DeleteMapping("/{userId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.noContent().build();
    }
}
