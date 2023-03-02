package com.mck.coffee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mck.coffee.model.Product;
import com.mck.coffee.service.OrderService;
import com.mck.coffee.service.ProductService;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public ResponseEntity<Page<Product>> getAll(Pageable pageable) {
		return ResponseEntity.ok(productService.findAll(pageable));
	}

	@GetMapping(value = "/id/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		Product obj = productService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping("/{name}")
	public ResponseEntity<List<Product>> getByName(@PathVariable String name) {
		List<Product> list = productService.findAllByNameContainingIgnoreCase(name);
		return ResponseEntity.ok().body(list);
	}

}
