package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Item;
import com.example.demo.service.ItemService;

@RestController
@RequestMapping("/items")
@CrossOrigin("http://localhost:3000")
public class ItemController {
	
	 @Autowired
    private ItemService service;
	 // ADD ITEM (Image + Data)
	    @PostMapping("/add")
	    public Item addItem(
	            @RequestParam("name") String name,
	            @RequestParam("description") String description,
	            @RequestParam("category") String category,
	            @RequestParam("price") Double price,
	            @RequestParam("image") MultipartFile image
	    ) throws IOException {

	        Item item = new Item();
	        item.setName(name);
	        item.setDescription(description);
	        item.setCategory(category);
	        item.setPrice(price);

	        return service.addItem(item, image);
	    }
	    // VIEW ALL
	    @GetMapping("/all")
	    public List<Item> getAllItems() {
	        return service.getAllItems();
	    }

	    // DELETE
	    @DeleteMapping("/delete/{id}")
	    public String deleteItem(@PathVariable Long id) {
	        service.deleteItem(id);
	        return "Item deleted successfully";
	    }



}
