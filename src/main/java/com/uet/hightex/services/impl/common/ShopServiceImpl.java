package com.uet.hightex.services.impl.common;

import com.uet.hightex.dtos.common.ShopInfoDto;
import com.uet.hightex.entities.common.Shop;
import com.uet.hightex.entities.manager.BeDistributorRequest;
import com.uet.hightex.enums.common.ShopStatus;
import com.uet.hightex.repositories.common.ShopRepository;
import com.uet.hightex.services.common.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class ShopServiceImpl implements ShopService {
    private final ShopRepository shopRepository;

    @Autowired
    public ShopServiceImpl(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createShop(BeDistributorRequest beDistributorRequest) {
        Shop shop = new Shop();

        shop.setShopCode(this.getShopCode());
        shop.setShopName(beDistributorRequest.getShopName());
        shop.setShopAddress(beDistributorRequest.getShopWarehouseAddress());
        shop.setPhoneNumber(beDistributorRequest.getShopPhone());
        shop.setEmail(beDistributorRequest.getShopEmail());
        shop.setOwnerCode(beDistributorRequest.getUserCode());
        shop.setShopStatus(ShopStatus.ACTIVE.getValue());
        shop.setAvatarUrl(null);
        shop.setNumberOfItems(0);
        shop.setNumberOfOrders(0);
        shop.setTotalRevenue(0.0);
        shop.setTotalSales(0);
        shop.setTaxCode(beDistributorRequest.getShopTaxCode());
        shop.setPaymentInfoId(null);

        shopRepository.save(shop);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ShopInfoDto getShopInfo(String shopCode) {
        Shop shop = shopRepository.findByShopCode(shopCode).orElse(null);
        if (shop == null) {
            return null;
        }

        ShopInfoDto shopInfoDto = new ShopInfoDto();
        shopInfoDto.setShopCode(shop.getShopCode());
        shopInfoDto.setShopName(shop.getShopName());
        shopInfoDto.setShopAddress(shop.getShopAddress());
        shopInfoDto.setPhoneNumber(shop.getPhoneNumber());
        shopInfoDto.setEmail(shop.getEmail());
        shopInfoDto.setOwnerCode(shop.getOwnerCode());
        shopInfoDto.setShopStatus(shop.getShopStatus());
        shopInfoDto.setAvatarUrl(shop.getAvatarUrl());
        shopInfoDto.setNumberOfItems(shop.getNumberOfItems());
        shopInfoDto.setNumberOfOrders(shop.getNumberOfOrders());
        shopInfoDto.setTotalRevenue(shop.getTotalRevenue());
        shopInfoDto.setTotalSales(shop.getTotalSales());
        shopInfoDto.setTaxCode(shop.getTaxCode());

        return shopInfoDto;
    }

    private String getShopCode() {
        return "SHOP" + System.currentTimeMillis();
    }
}
