package com.uet.hightex.services.impl.common;

import com.uet.hightex.dtos.item.ResponseItem;
import com.uet.hightex.entities.common.Item;
import com.uet.hightex.repositories.common.ItemRepository;
import com.uet.hightex.services.common.ItemService;
import com.uet.hightex.utils.MapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
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

                    return responseItem;
                }
        ).collect(Collectors.toList());
    }
}
