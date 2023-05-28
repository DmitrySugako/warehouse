package com.sugako.service.implementations;

import com.sugako.domain.Item;
import com.sugako.repository.ItemRepository;
import com.sugako.requests.ItemCreateRequest;
import com.sugako.requests.ItemUpdateRequest;
import com.sugako.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository repository;
    private final ConversionService conversionService;

    @Transactional
    @Override
    public Item createItem(ItemCreateRequest itemCreateRequest) {

        Item item = conversionService.convert(itemCreateRequest, Item.class);
        item = repository.save(item);

        return item;
    }

    @Transactional
    @Override
    public Item updateItem(ItemUpdateRequest itemUpdateRequest) {

        Item item = conversionService.convert(itemUpdateRequest, Item.class);
        item = repository.save(item);

        return item;
    }

    @Override
    public Item findById(Long id) {
        return repository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Item with id " + id + " not found"));
    }


}
