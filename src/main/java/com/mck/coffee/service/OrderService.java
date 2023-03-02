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
import com.mck.coffee.repository.PaymentRepository;
import com.mck.coffee.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private PaymentRepository paymentRepository;

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

	public Order putOrderItem(Long orderId, OrderItemDTO orderItemDTO) {
	    Order order = orderRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException("Order not found"));
	    Product product = productRepository.findById(orderItemDTO.getIdProduct()).orElseThrow(() -> new EntityNotFoundException("Product not found"));

	    if (orderItemRepository.existsByOrderAndProduct(order, product)) {
	        OrderItem orderItem = orderItemRepository.findByOrderAndProduct(order, product);
	        orderItem.setQuantity(orderItemDTO.getQuantity());
	        double price = orderItemDTO.getQuantity() * product.getPrice();
	        orderItem.setSubPrice(price);
	        orderItemRepository.save(orderItem);
	    } else {
	        throw new EntityNotFoundException("OrderItem not found");
	    }

	    double totalPrice = order.getOrderItems().stream()
	            .mapToDouble(item -> item.getSubPrice())
	            .sum();
	    order.setTotalPrice(totalPrice);
	    orderRepository.save(order);

	    return order;
	}




//private double calculatePaymentFee(String paymentType, double totalPrice) {
//   
//    if (paymentType.equals("creditCard")) {
//        return totalPrice * 0.02;
//    } else {
//        return totalPrice;
//    }
//}
	
}
