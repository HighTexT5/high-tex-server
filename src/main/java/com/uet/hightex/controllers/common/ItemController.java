package com.uet.hightex.controllers.common;

import com.uet.hightex.dtos.base.BaseResponse;
import com.uet.hightex.dtos.item.ResponseItem;
import com.uet.hightex.dtos.item.ResponseItemDetail;
import com.uet.hightex.enums.AppConstant;
import com.uet.hightex.services.common.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/all")
    public BaseResponse<List<ResponseItem>> getAllItems() {
        return new BaseResponse<>(AppConstant.REQUEST_SUCCESS.getValue(), "Success", itemService.getAllItems());
    }

    @GetMapping("/detail")
    public BaseResponse<ResponseItemDetail> getItemDetail(@RequestParam Long id) throws IOException {
        return new BaseResponse<>(AppConstant.REQUEST_SUCCESS.getValue(), "Success", itemService.getItemDetail(id));
    }

    @GetMapping("/search")
    public BaseResponse<List<ResponseItem>> searchItems(@RequestParam String keyword) {
        return new BaseResponse<>(AppConstant.REQUEST_SUCCESS.getValue(), "Success", itemService.getItemsBySearch(keyword));
    }
}
