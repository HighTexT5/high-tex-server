package com.uet.hightex.dtos.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOrderForDistributorInList {
    @Schema(description = "Mã đơn hàng")
    private String orderCode;

    @Schema(description = "Mã sản phẩm")
    private String itemCode;

    @Schema(description = "Tên sản phẩm")
    private String itemName;

    @Schema(description = "Số lượng")
    private int quantity;

    @Schema(description = "Tổng giá")
    private double totalPrice;

    @Schema(description = "Trạng thái")
    private String status;

    private String userCode;
}
