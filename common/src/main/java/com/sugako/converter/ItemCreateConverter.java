package com.sugako.converter;

import com.sugako.domain.Item;
import com.sugako.requests.ItemCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component

public class ItemCreateConverter extends ItemBaseConverter<ItemCreateRequest, Item> {

    @Override
    public Item convert(ItemCreateRequest request) {

        Item item = new Item();

        item.setCreated(new Timestamp(new Date().getTime()));

        return doConvert(item, request);
    }
}
