package com.uet.hightex.repositories.common;

import com.uet.hightex.entities.common.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByIsActiveTrueOrderByRatingDesc();

    Optional<Item> findByItemCode(String itemCode);

    List<Item> findAllByShopCodeAndIsActiveTrue(String shopCode);

    List<Item> findAllByItemNameContainingAndIsActiveTrue(String itemName);
}
