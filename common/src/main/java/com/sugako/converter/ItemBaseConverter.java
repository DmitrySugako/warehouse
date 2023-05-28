package com.sugako.converter;

import com.sugako.domain.Item;
import com.sugako.requests.ItemCreateRequest;
import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;
import java.util.Date;

public abstract class ItemBaseConverter<S, T> implements Converter<S, T> {

    public Item doConvert(Item itemForUpdate, ItemCreateRequest request) {

        itemForUpdate.setSkuCode(request.getSkuCode());
        itemForUpdate.setDescription(request.getDescription());
        itemForUpdate.setCategory(request.getCategory());
        itemForUpdate.setChanged(new Timestamp(new Date().getTime()));
        itemForUpdate.setIsDeleted(false);

        return itemForUpdate;
    }
}
