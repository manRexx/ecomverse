package com.ecomverse.cart.repository;

import com.ecomverse.cart.model.CartItem;
import org.hibernate.cache.internal.QueryResultsCacheImpl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCartId(Long cartId);
}
