package com.uet.hightex.services.common;

import com.uet.hightex.dtos.order.ResponseOrderForDistributorInList;

import java.io.IOException;
import java.util.List;

public interface OrderService {
    void createOrderFromCart(String userCode) throws IOException;

    List<ResponseOrderForDistributorInList> getListOrderForDistributor(String distributorCode);

    void acceptOrder(String orderCode);

    List<ResponseOrderForDistributorInList> getListOrderAcceptedAndNotDeliveredForDistributor(String distributorCode);
}
