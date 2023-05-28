package com.sugako.controller;


import com.sugako.domain.Item;
import com.sugako.exception.IllegalRequestException;
import com.sugako.repository.ItemRepository;
import com.sugako.requests.ItemCreateRequest;
import com.sugako.requests.ItemUpdateRequest;
import com.sugako.service.implementations.ItemServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("rest/item")
@RequiredArgsConstructor
public class ItemRestController {

    private final ItemServiceImpl itemService;

    private final ItemRepository itemRepository;

    @PostMapping
    public ResponseEntity<Item> createItem(@Valid @RequestBody ItemCreateRequest itemRequest,
                                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new IllegalRequestException(bindingResult);
        }

        Item item = itemService.createItem(itemRequest);
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Item> updateItem(@Valid @RequestBody ItemUpdateRequest itemRequest,
                                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new IllegalRequestException(bindingResult);
        }

        Item updateItem = itemService.updateItem(itemRequest);
        return new ResponseEntity<>(updateItem, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemRepository.findAll();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable("id") Long id) {

        Optional<Item> item = itemRepository.findById(id);

        return item.map(ResponseEntity::ok).orElseThrow(() ->
                new EntityNotFoundException("Item with id " + id + " not found"));
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<String> deactivate(@PathVariable("id") Long id) {

        Optional<Item> searchItem = itemRepository.findById(id);

        if (searchItem.isPresent()) {
            Item item = searchItem.get();
            item.setIsDeleted(true);
            itemRepository.save(item);
            return new ResponseEntity<>("Item successfully deactivated", HttpStatus.OK);
        } else {
            throw new EntityNotFoundException(("Item with id " + id + " not found"));
        }
    }


}
