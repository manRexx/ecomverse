package com.ecomverse.cart.mapper;

import com.ecomverse.cart.dto.CartDTO;
import com.ecomverse.cart.dto.CartItemDTO;
import com.ecomverse.cart.model.Cart;
import com.ecomverse.cart.model.CartItem;

import java.util.List;
import java.util.stream.Collectors;

public class CartMapper {

    public static CartDTO toDTO(Cart cart) {
        return CartDTO.builder()
                .id(cart.getId())
                .userId(cart.getUserId())
                .cartItems(cart.getCartItems().stream().map(CartMapper::toItemDTO).collect(Collectors.toList()))
                .build();
    }

    public static CartItemDTO toItemDTO(CartItem item) {
        return CartItemDTO.builder()
                .id(item.getId())
                .productId(item.getProductId())
                .quantity(item.getQuantity())
                .build();
    }

    public static Cart toEntity(CartDTO cartDTO) {
        Cart cart = Cart.builder()
                .id(cartDTO.getId())
                .userId(cartDTO.getUserId())
                .build();

        if (cartDTO.getCartItems() != null) {
            List<CartItem> items = cartDTO.getCartItems().stream()
                    .map(dto -> {
                        CartItem item = new CartItem();
                        item.setId(dto.getId());
                        item.setProductId(dto.getProductId());
                        item.setQuantity(dto.getQuantity());
                        item.setCart(cart); // Set back-reference
                        return item;
                    }).collect(Collectors.toList());
            cart.setCartItems(items);
        }

        return cart;
    }
}
