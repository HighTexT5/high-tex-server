package com.uet.hightex.services.impl.common;

import com.uet.hightex.dtos.item.ResponseCartOfItem;
import com.uet.hightex.dtos.item.ResponseItemInCart;
import com.uet.hightex.entities.common.Item;
import com.uet.hightex.entities.common.ShoppingCart;
import com.uet.hightex.repositories.common.ItemRepository;
import com.uet.hightex.repositories.common.ShoppingCartRepository;
import com.uet.hightex.services.common.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Slf4j
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository,
                                   ItemRepository itemRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addProductToCart(String userCode, String itemCode, Integer quantity) throws IOException {
        ShoppingCart cart = shoppingCartRepository.findByUserCodeAndIsDeletedFalse(userCode).orElse(null);
        if (cart == null) {
            cart = new ShoppingCart();
            cart.setUserCode(userCode);
            String[] itemCodes = {itemCode};
            int[] quantities = {quantity};
            cart.setListItemCodeArray(itemCodes);
            cart.setListItemQuantityArray(quantities);
            Item item = itemRepository.findByItemCode(itemCode).orElse(null);
            if (item != null) {
                cart.setTotalPrice(item.getCurrentPrice() * quantity);
                item.setQuantity(item.getQuantity() - quantity);
                itemRepository.save(item);
            }
            shoppingCartRepository.save(cart);
        } else {
            String[] itemCodes = cart.getListItemCodeArray();
            int[] quantities = cart.getListItemQuantityArray();
            int index = -1;
            for (int i = 0; i < itemCodes.length; i++) {
                if (itemCodes[i].equals(itemCode)) {
                    index = i;
                    break;
                }
            }
            if (index == -1) {
                String[] newItemCodes = new String[itemCodes.length + 1];
                int[] newQuantities = new int[quantities.length + 1];
                System.arraycopy(itemCodes, 0, newItemCodes, 0, itemCodes.length);
                System.arraycopy(quantities, 0, newQuantities, 0, quantities.length);
                newItemCodes[itemCodes.length] = itemCode;
                newQuantities[quantities.length] = quantity;
                cart.setListItemCodeArray(newItemCodes);
                cart.setListItemQuantityArray(newQuantities);
                Item item = itemRepository.findByItemCode(itemCode).orElse(null);
                if (item != null) {
                    cart.setTotalPrice(cart.getTotalPrice() + item.getCurrentPrice() * quantity);
                    item.setQuantity(item.getQuantity() - quantity);
                    itemRepository.save(item);
                }
            } else {
                quantities[index] += quantity;
                cart.setListItemQuantityArray(quantities);
                Item item = itemRepository.findByItemCode(itemCode).orElse(null);
                if (item != null) {
                    cart.setTotalPrice(cart.getTotalPrice() + item.getCurrentPrice() * quantity);
                    item.setQuantity(item.getQuantity() - quantity);
                    itemRepository.save(item);
                }
            }
            shoppingCartRepository.save(cart);
        }
    }

    @Override
    public ResponseCartOfItem getCartOfItem(String userCode) throws IOException {
        ShoppingCart cart = shoppingCartRepository.findByUserCodeAndIsDeletedFalse(userCode).orElse(null);
        if (cart == null) {
            return null;
        }
        ResponseCartOfItem responseCartOfItem = new ResponseCartOfItem();
        responseCartOfItem.setTotalPrice(cart.getTotalPrice());
        String[] itemCodes = cart.getListItemCodeArray();
        int[] quantities = cart.getListItemQuantityArray();
        for (int i = 0; i < itemCodes.length; i++) {
            Item item = itemRepository.findByItemCode(itemCodes[i]).orElse(null);
            if (item != null) {
                responseCartOfItem.addItem(new ResponseItemInCart(item.getItemName(), quantities[i], item.getCurrentPrice(), item.getThumbnailUrl()));
            }
        }
        return responseCartOfItem;
    }
}
