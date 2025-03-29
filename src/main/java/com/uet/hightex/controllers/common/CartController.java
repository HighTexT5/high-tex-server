package com.uet.hightex.controllers.common;

import com.uet.hightex.dtos.base.BaseResponse;
import com.uet.hightex.dtos.item.ResponseCartOfItem;
import com.uet.hightex.enums.AppConstant;
import com.uet.hightex.services.common.ShoppingCartService;
import com.uet.hightex.services.support.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/shopping-cart")
public class CartController {
    private final ShoppingCartService shoppingCartService;
    private final BaseService baseService;

    @Autowired
    public CartController(ShoppingCartService shoppingCartService, BaseService baseService) {
        this.shoppingCartService = shoppingCartService;
        this.baseService = baseService;
    }

    @PostMapping("/add")
    public BaseResponse<String> addProductToCart(@RequestParam String itemCode, @RequestParam Integer quantity) {
        String userCode = baseService.getUserCode();
        try {
            shoppingCartService.addProductToCart(userCode, itemCode, quantity);
        } catch (Exception e) {
            throw new RuntimeException("Add product to cart failed");
        }
        return new BaseResponse<>(AppConstant.REQUEST_SUCCESS.getValue(), "Success", "Add product to cart successfully");
    }

    @GetMapping("/get-cart")
    public BaseResponse<ResponseCartOfItem> getCartOfItem() throws IOException {
        String userCode = baseService.getUserCode();
        return new BaseResponse<>(AppConstant.REQUEST_SUCCESS.getValue(), "Success", shoppingCartService.getCartOfItem(userCode));
    }
}
