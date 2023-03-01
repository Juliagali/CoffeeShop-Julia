package com.mck.coffee.config;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.mck.coffee.model.Category;
import com.mck.coffee.model.Order;
import com.mck.coffee.model.OrderItem;
import com.mck.coffee.model.Payment;
import com.mck.coffee.model.Product;
import com.mck.coffee.model.User;
import com.mck.coffee.model.enums.OrderStatus;
import com.mck.coffee.repository.CategoryRepository;
import com.mck.coffee.repository.OrderItemRepository;
import com.mck.coffee.repository.OrderRepository;
import com.mck.coffee.repository.ProductRepository;
import com.mck.coffee.repository.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public void run(String... args) throws Exception {

		// Seed data for User object
//		Long userId = 1L;
//		String userName = "John Smith";
//		String userEmail = "john.smith@example.com";
//		String userPassword = "password";
//		String userCep = "12345678";
		
		
		
		User user1 = new User(1L, "John Smith", "john.smith@example.com", "password", "12345678");

		// Seed data for Category object
		Category category1 = new Category(1L, "Electronics");

		Product product1 = new Product(1L, "Smartphone", "A powerful and modern smartphone.", 799.99,
				"https://example.com/product.jpg", category1);

		OrderItem orderItem1 = new OrderItem(1L, 2, null, null, product1);
		orderItem1.calculateSubtotal();

		Payment payment1 = new Payment(null, Instant.now(), null);

		Order order1 = new Order(null, OrderStatus.SHIPPED, payment1, 999.98,
				new ArrayList<>(Collections.singletonList(orderItem1)));
		order1.updateTotalPrice();
		
		
		user1.getOrders().add(order1);
		orderItem1.setOrder(order1);
		orderItem1.setProduct(product1);
		order1.getOrderItems().add(orderItem1);
		payment1.setOrder(order1);
		product1.setCategory(category1);

		userRepository.saveAll(Arrays.asList(user1));
		orderRepository.saveAll(Arrays.asList(order1));
		categoryRepository.saveAll(Arrays.asList(category1));
		productRepository.saveAll(Arrays.asList(product1));
		orderItemRepository.saveAll(Arrays.asList(orderItem1));

	}
}
