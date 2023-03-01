package com.mck.coffee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mck.coffee.DTO.OrderItemDTO;
import com.mck.coffee.model.Order;
import com.mck.coffee.model.OrderItem;
import com.mck.coffee.model.Product;
import com.mck.coffee.repository.OrderItemRepository;
import com.mck.coffee.repository.OrderRepository;
import com.mck.coffee.repository.ProductRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	public Order postOrderAndItem(OrderItemDTO orderItemDTO) {

		Order order = new Order();

		orderRepository.save(order);

		Product product = productRepository.findById(orderItemDTO.getIdProduct()).get();
		
		OrderItem orderItem = new OrderItem();

		orderItem.setQuantity(orderItemDTO.getQuantity());
		orderItem.setOrder(order);
		orderItem.setProduct(product);

		double price = product.getPrice() * orderItem.getQuantity();
		orderItem.setSubPrice(price);
		order.setTotalPrice(price);
		orderRepository.save(order);

		orderItemRepository.save(orderItem);

		order.setOrderItems(List.of(orderItem));
		return order;
	}

}
