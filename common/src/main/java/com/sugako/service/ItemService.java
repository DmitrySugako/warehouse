package com.sugako.service;

import com.sugako.domain.Item;
import com.sugako.requests.ItemCreateRequest;
import com.sugako.requests.ItemUpdateRequest;

public interface ItemService {

    Item createItem(ItemCreateRequest itemCreateRequest);

    Item updateItem(ItemUpdateRequest itemUpdateRequest);

    Item findById(Long id);


}
