package com.uet.hightex.controllers.common;

import com.uet.hightex.dtos.base.BaseResponse;
import com.uet.hightex.dtos.item.AddProductToCartRequestDto;
import com.uet.hightex.dtos.item.ResponseCartOfItem;
import com.uet.hightex.dtos.item.UpdateCartItemQuantityRequestDto;
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
    public BaseResponse<String> addProductToCart(@RequestBody AddProductToCartRequestDto request) {
        String userCode = baseService.getUserCode();
        try {
            shoppingCartService.addProductToCart(userCode, request.getItemCode(), request.getQuantity());
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

    // PATCH Controller Method
//    @PatchMapping("/update-quantity")
//    public BaseResponse<String> updateCartItemQuantity(@RequestBody UpdateCartItemQuantityRequestDto request) {
//        String userCode = baseService.getUserCode();
//        try {
//            shoppingCartService.updateItemQuantity(userCode, request.getItemCode(), request.getQuantity());
//            return new BaseResponse<>(
//                    AppConstant.REQUEST_SUCCESS.getValue(),
//                    "Success",
//                    "Item quantity updated successfully"
//            );
//        } catch (Exception e) {
//            throw new RuntimeException("Update item quantity failed: " + e.getMessage());
//        }
//    }
}
