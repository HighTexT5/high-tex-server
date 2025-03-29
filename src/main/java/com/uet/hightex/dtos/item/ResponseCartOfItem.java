package com.uet.hightex.dtos.item;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCartOfItem {
    @Schema(description = "Items")
    private List<ResponseItemInCart> items;

    @Schema(description = "Total price")
    private double totalPrice;

    public void addItem(ResponseItemInCart item) {
        if (items == null) {
            items = new java.util.ArrayList<>();
        }
        items.add(item);
    }
}
