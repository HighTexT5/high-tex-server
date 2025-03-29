package com.uet.hightex.entities.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uet.hightex.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.IOException;

@SuperBuilder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SHOPPING_CART")
public class ShoppingCart extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_CODE")
    private String userCode;

    @Column(name = "LIST_ITEM_CODE", columnDefinition = "JSON")
    private String listItemCode;

    @Column(name = "LIST_ITEM_QUANTITY", columnDefinition = "JSON")
    private String listItemQuantity;

    @Column(name = "TOTAL_PRICE")
    private double totalPrice;

    @Column(name = "IS_DELETED")
    private boolean isDeleted;

    public String[] getListItemCodeArray() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(this.listItemCode, String[].class);
    }

    public void setListItemCodeArray(String[] listItemCodeArray) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        this.listItemCode = mapper.writeValueAsString(listItemCodeArray);
    }

    public int[] getListItemQuantityArray() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(this.listItemQuantity, int[].class);
    }

    public void setListItemQuantityArray(int[] listItemQuantityArray) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        this.listItemQuantity = mapper.writeValueAsString(listItemQuantityArray);
    }
}
