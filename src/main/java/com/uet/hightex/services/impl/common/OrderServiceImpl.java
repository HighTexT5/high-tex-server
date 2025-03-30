package com.uet.hightex.services.impl.common;

import com.uet.hightex.dtos.order.ResponseOrderForDistributorInList;
import com.uet.hightex.entities.common.Item;
import com.uet.hightex.entities.common.Order;
import com.uet.hightex.entities.common.Shop;
import com.uet.hightex.entities.common.ShoppingCart;
import com.uet.hightex.enums.common.RequestStatus;
import com.uet.hightex.repositories.common.ItemRepository;
import com.uet.hightex.repositories.common.OrderRepository;
import com.uet.hightex.repositories.common.ShopRepository;
import com.uet.hightex.repositories.common.ShoppingCartRepository;
import com.uet.hightex.services.common.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ItemRepository itemRepository;
    private final ShopRepository shopRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            ShoppingCartRepository shoppingCartRepository,
                            ItemRepository itemRepository,
                            ShopRepository shopRepository) {
        this.orderRepository = orderRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.itemRepository = itemRepository;
        this.shopRepository = shopRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createOrderFromCart(String userCode) throws IOException {
        ShoppingCart cart = shoppingCartRepository.findByUserCodeAndIsDeletedFalse(userCode)
                .orElseThrow(() -> new RuntimeException("Shopping cart not found"));

        // Create order from cart
        String[] itemCodes = cart.getListItemCodeArray();
        int[] itemQuantities = cart.getListItemQuantityArray();

        for (int i = 0; i < itemCodes.length; i++) {
            Item item = itemRepository.findByItemCode(itemCodes[i])
                    .orElseThrow(() -> new RuntimeException("Item not found"));
            Order order = Order.builder()
                    .orderCode("ORDER_" + System.currentTimeMillis())
                    .userCode(userCode)
                    .itemCode(itemCodes[i])
                    .quantity(itemQuantities[i])
                    .totalPrice(item.getCurrentPrice() * itemQuantities[i])
                    .isPaid(false)
                    .isDelivered(false)
                    .isCanceled(false)
                    .status(RequestStatus.PENDING.getCode())
                    .build();
            orderRepository.save(order);
        }
        cart.setDeleted(true);
        shoppingCartRepository.save(cart);
    }

    @Override
    public List<ResponseOrderForDistributorInList> getListOrderForDistributor(String distributorCode) {
        Shop shop = shopRepository.findByOwnerCode(distributorCode)
                .orElseThrow(() -> new RuntimeException("Shop not found"));
        List<Item> items = itemRepository.findAllByShopCodeAndIsActiveTrue(shop.getShopCode());
        List<ResponseOrderForDistributorInList> responseOrders = new ArrayList<>();
        for (Item item: items) {
            List<Order> orders = orderRepository.findAllByItemCodeAndIsCanceledFalseAndStatus(item.getItemCode(), RequestStatus.PENDING.getCode());
            for (Order order : orders) {
                responseOrders.add(new ResponseOrderForDistributorInList(
                        order.getOrderCode(),
                        order.getItemCode(),
                        item.getItemName(),
                        order.getQuantity(),
                        order.getTotalPrice(),
                        Objects.requireNonNull(RequestStatus.fromCode(order.getStatus())).getDescription()
                ));
            }
        }

        return responseOrders;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void acceptOrder(String orderCode) {
        Order order = orderRepository.findByOrderCode(orderCode)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(RequestStatus.APPROVED.getCode());
        orderRepository.save(order);

        ///  May have delivery process here
    }

    @Override
    public List<ResponseOrderForDistributorInList> getListOrderAcceptedAndNotDeliveredForDistributor(String distributorCode) {
        Shop shop = shopRepository.findByOwnerCode(distributorCode)
                .orElseThrow(() -> new RuntimeException("Shop not found"));
        List<Item> items = itemRepository.findAllByShopCodeAndIsActiveTrue(shop.getShopCode());
        List<ResponseOrderForDistributorInList> responseOrders = new ArrayList<>();
        for (Item item: items) {
            List<Order> orders = orderRepository.findAllByItemCodeAndIsDeliveredFalseAndStatus(item.getItemCode(), RequestStatus.APPROVED.getCode());
            for (Order order : orders) {
                responseOrders.add(new ResponseOrderForDistributorInList(
                        order.getOrderCode(),
                        order.getItemCode(),
                        item.getItemName(),
                        order.getQuantity(),
                        order.getTotalPrice(),
                        Objects.requireNonNull(RequestStatus.fromCode(order.getStatus())).getDescription()
                ));
            }
        }

        return responseOrders;
    }
}
