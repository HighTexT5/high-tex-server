package com.uet.hightex.repositories.common;

import com.uet.hightex.entities.common.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByUserCodeAndIsDeletedFalse(String userCode);
}
