package com.mck.coffee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mck.coffee.model.Order;
import com.mck.coffee.model.OrderItem;
import com.mck.coffee.model.Product;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

	boolean existsByOrderAndProduct(Order order, Product product);

	OrderItem findByOrderAndProduct(Order order, Product product);



	
}
