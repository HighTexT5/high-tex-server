package com.uet.hightex.repositories.common;

import com.uet.hightex.entities.common.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderCode(String orderCode);

    List<Order> findAllByUserCode(String userCode);

    List<Order> findAllByItemCodeAndIsCanceledFalseAndStatus(String itemCode, String status);

    List<Order> findAllByItemCodeAndIsDeliveredFalseAndStatus(String itemCode, String status);
}
