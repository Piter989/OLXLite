package com.olxLite.olxLite_bff.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olxLite.olxLite_bff.model.Item;

@RestController
@RequestMapping("/item")
public class ItemController {

    private final Map<UUID, Item> items = new HashMap<>();

    @GetMapping
    public List<Item> getAllItems() {
        return new ArrayList<>(items.values());
    }

    @GetMapping("/{id}")
    public Item getItem(@PathVariable UUID id) {
        return items.get(id);
    }

    @PostMapping
    public Item createItem(@RequestBody Item item) {
        UUID id = UUID.randomUUID();
        item.setId(id);
        items.put(id, item);
        return item;
    }

    @PutMapping("/{id}")
    public Item updateItem(@PathVariable UUID id, @RequestBody Item updatedItem) {
        updatedItem.setId(id);
        items.put(id, updatedItem);
        return updatedItem;
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable UUID id) {
        items.remove(id);
    }
}
