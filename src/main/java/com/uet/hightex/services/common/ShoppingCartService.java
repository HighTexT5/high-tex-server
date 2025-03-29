package com.uet.hightex.services.common;

import com.uet.hightex.dtos.item.ResponseCartOfItem;

import java.io.IOException;

public interface ShoppingCartService {
    void addProductToCart(String userCode, String itemCode, Integer quantity) throws IOException;

    ResponseCartOfItem getCartOfItem(String userCode) throws IOException;
}
