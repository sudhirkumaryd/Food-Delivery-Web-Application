package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Item;
import com.example.demo.repo.ItemRepository;

@Service
public class ItemService {
	
	@Autowired
    private ItemRepository repository;
	
	@Value("${file.upload-dir}")
    private String uploadDir;
	 public Item addItem(Item item, MultipartFile file) throws IOException {

	        // Create upload folder if not exists
	        File folder = new File(uploadDir);
	        if (!folder.exists()) {
	            folder.mkdir();
	        }

	        // Save file
	        String fileName = file.getOriginalFilename();
	        Path path = Paths.get(uploadDir, fileName);
	        Files.write(path, file.getBytes());

	        item.setImageName(fileName);

	        return repository.save(item);
	    }
	 public List<Item> getAllItems() {
	        return repository.findAll();
	    }

	    public void deleteItem(Long id) {
	        repository.deleteById(id);
	    }


}
