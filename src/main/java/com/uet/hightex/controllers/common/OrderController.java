package com.uet.hightex.controllers.common;

import com.uet.hightex.dtos.base.BaseResponse;
import com.uet.hightex.dtos.order.ResponseOrderForDistributorInList;
import com.uet.hightex.enums.AppConstant;
import com.uet.hightex.services.common.OrderService;
import com.uet.hightex.services.support.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;
    private final BaseService baseService;

    public OrderController(OrderService orderService,
                           BaseService baseService) {
        this.orderService = orderService;
        this.baseService = baseService;
    }

    @PostMapping("/all/create-order-from-cart")
    public ResponseEntity<?> createOrderFromCart(@RequestParam String addressCode) {
        try {
            String userCode = baseService.getUserCode();
            orderService.createOrderFromCart(userCode, addressCode);
            return ResponseEntity.ok("Đặt mua hàng thành công");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/distributor/list-order-for-distributor")
    public BaseResponse<List<ResponseOrderForDistributorInList>> getListOrderForDistributor() {
        String distributorCode = baseService.getUserCode();
        return new BaseResponse<>(AppConstant.REQUEST_SUCCESS.getValue(), "Success",orderService.getListOrderForDistributor(distributorCode));
    }

    @PostMapping("/distributor/accept-order")
    public ResponseEntity<?> acceptOrder(@RequestParam String orderCode) {
        try {
            orderService.acceptOrder(orderCode);
            return ResponseEntity.ok("Xác nhận đơn hàng thành công");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/distributor/list-order-accepted-and-not-delivered-for-distributor")
    public BaseResponse<List<ResponseOrderForDistributorInList>> getListOrderAcceptedAndNotDeliveredForDistributor() {
        String distributorCode = baseService.getUserCode();
        return new BaseResponse<>(AppConstant.REQUEST_SUCCESS.getValue(), "Success", orderService.getListOrderAcceptedAndNotDeliveredForDistributor(distributorCode));
    }
}
