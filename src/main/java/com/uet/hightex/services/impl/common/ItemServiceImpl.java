package com.uet.hightex.services.impl.common;

import com.uet.hightex.dtos.item.ResponseItem;
import com.uet.hightex.dtos.item.ResponseItemDetail;
import com.uet.hightex.dtos.request.items.SmartphoneRequest;
import com.uet.hightex.entities.common.Item;
import com.uet.hightex.entities.common.Shop;
import com.uet.hightex.entities.items.SmartphoneInfo;
import com.uet.hightex.repositories.common.ItemRepository;
import com.uet.hightex.repositories.common.ShopRepository;
import com.uet.hightex.repositories.items.SmartphoneInfoRepository;
import com.uet.hightex.services.common.ItemService;
import com.uet.hightex.utils.MapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final ShopRepository shopRepository;
    private final SmartphoneInfoRepository smartphoneInfoRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, ShopRepository shopRepository, SmartphoneInfoRepository smartphoneInfoRepository) {
        this.itemRepository = itemRepository;
        this.shopRepository = shopRepository;
        this.smartphoneInfoRepository = smartphoneInfoRepository;
    }

    @Override
    public List<ResponseItem> getAllItems() {
        List<Item> items = itemRepository.findAllByIsActiveTrueOrderByRatingDesc();

        return items.stream().map(
                item -> {
                    ResponseItem responseItem = new ResponseItem();
                    MapperUtils.map(item, responseItem);
                    responseItem.setPrice(item.getCurrentPrice());
                    responseItem.setName(item.getItemName());
                    responseItem.setImageURL(item.getThumbnailUrl());

                    return responseItem;
                }
        ).collect(Collectors.toList());
    }

    @Override
    public ResponseItemDetail getItemDetail(Long id) throws IOException {
        Item item = itemRepository.findById(id).orElse(null);
        if (item == null || !item.isActive() || item.isDeleted()) {
            return null;
        }

        ResponseItemDetail responseItemDetail = new ResponseItemDetail();
        MapperUtils.map(item, responseItemDetail);
        shopRepository.findByShopCode(item.getShopCode()).ifPresent(shop -> responseItemDetail.setShopName(shop.getShopName()));
        if (item.getFileUrls() != null && !item.getFileUrls().isEmpty()) {
            responseItemDetail.setFileUrls(item.getListUrlsArray());
        }
        responseItemDetail.setDetail(getDetail(item.getCategory(), item.getItemInfoId()));
        return responseItemDetail;
    }

    @Override
    public List<ResponseItem> getItemsBySearch(String search) {
        List<Item> items = itemRepository.findAllByItemNameContainingAndIsActiveTrue(search);
        return items.stream().map(
                item -> {
                    ResponseItem responseItem = new ResponseItem();
                    MapperUtils.map(item, responseItem);
                    responseItem.setPrice(item.getCurrentPrice());
                    responseItem.setName(item.getItemName());
                    responseItem.setImageURL(item.getThumbnailUrl());

                    return responseItem;
                }
        ).collect(Collectors.toList());
    }

    private Object getDetail(String category, Long infoId) {
        switch (category) {
            case "SMARTPHONE":
            case "smartphone" :
            {
                SmartphoneInfo smartphoneInfo = smartphoneInfoRepository.findById(infoId).orElse(null);
                SmartphoneRequest smartphoneRequest = new SmartphoneRequest();
                if (smartphoneInfo == null) {
                    return null;
                }
                MapperUtils.map(smartphoneInfo, smartphoneRequest);
                return smartphoneRequest;
            }
            default:
                return null;
        }
    }
}
