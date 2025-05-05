package com.uet.hightex.services.common;

import com.uet.hightex.dtos.item.ResponseItem;
import com.uet.hightex.dtos.item.ResponseItemDetail;

import java.io.IOException;
import java.util.List;

public interface ItemService {
    List<ResponseItem> getAllItems();

    ResponseItemDetail getItemDetail(Long id) throws IOException;

    List<ResponseItem> getItemsBySearch(String keyword);
}
