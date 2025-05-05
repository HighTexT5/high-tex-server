package com.uet.hightex.services.common;

import com.uet.hightex.dtos.common.ShopInfoDto;
import com.uet.hightex.entities.manager.BeDistributorRequest;

public interface ShopService {
    void createShop(BeDistributorRequest beDistributorRequest);

    ShopInfoDto getShopInfo(String shopCode);
}
