package com.uet.hightex.repositories.common;

import com.uet.hightex.entities.common.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByIsActiveTrueOrderByRatingDesc();
}
