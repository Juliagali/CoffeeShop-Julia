package com.mck.coffee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mck.coffee.model.Product;
import com.mck.coffee.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;


	public Page<Product> findAll(Pageable pageable){
		Page<Product> product = productRepository.findAll(pageable);
		return product;
	}
	
	public Product findById(Long id) {
		Optional<Product> obj = productRepository.findById(id);
		return obj.get();
	}

	public List<Product> findAllByNameContainingIgnoreCase(String name) {
		return productRepository.findAllByNameContainingIgnoreCase(name);
	}
	
}
