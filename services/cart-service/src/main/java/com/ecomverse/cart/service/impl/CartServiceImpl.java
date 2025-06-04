package com.ecomverse.cart.service.impl;

import com.ecomverse.cart.dto.CartDTO;
import com.ecomverse.cart.dto.CartItemDTO;
import com.ecomverse.cart.mapper.CartMapper;
import com.ecomverse.cart.model.Cart;
import com.ecomverse.cart.model.CartItem;
import com.ecomverse.cart.repository.CartItemRepository;
import com.ecomverse.cart.repository.CartRepository;
import com.ecomverse.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public CartDTO getCartByUserId(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> cartRepository.save(Cart.builder().userId(userId).cartItems(List.of()).build()));
        return CartMapper.toDTO(cart);
    }

    @Override
    public CartDTO addItemToCart(Long userId, CartItemDTO itemDTO) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> cartRepository.save(Cart.builder().userId(userId).build()));

        Optional<CartItem> existingItemOpt = cart.getCartItems().stream()
                .filter(i -> i.getProductId().equals(itemDTO.getProductId()))
                .findFirst();

        if (existingItemOpt.isPresent()) {
            CartItem existingItem = existingItemOpt.get();
            existingItem.setQuantity(existingItem.getQuantity() + itemDTO.getQuantity());
        } else {
            CartItem newItem = CartItem.builder()
                    .productId(itemDTO.getProductId())
                    .quantity(itemDTO.getQuantity())
                    .cart(cart)
                    .build();
            cart.getCartItems().add(newItem);
        }

        cartRepository.save(cart);
        return CartMapper.toDTO(cart);
    }

    @Override
    public CartDTO updateItemQuantity(Long userId, Long productId, int quantity) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cart.getCartItems().stream()
                .filter(i -> i.getProductId().equals(productId))
                .findFirst()
                .ifPresent(i -> i.setQuantity(quantity));

        cartRepository.save(cart);
        return CartMapper.toDTO(cart);
    }

    @Override
    public CartDTO removeItemFromCart(Long userId, Long productId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cart.getCartItems().removeIf(i -> i.getProductId().equals(productId));
        cartRepository.save(cart);
        return CartMapper.toDTO(cart);
    }

    @Override
    public void clearCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.getCartItems().clear();
        cartRepository.save(cart);
    }
}
