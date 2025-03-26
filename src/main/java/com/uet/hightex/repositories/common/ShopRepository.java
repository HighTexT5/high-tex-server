package com.uet.hightex.repositories.common;

import com.uet.hightex.entities.common.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    Optional<Shop> findByShopCode(String shopCode);

    Optional<Shop> findByOwnerCode(String ownerCode);
}
