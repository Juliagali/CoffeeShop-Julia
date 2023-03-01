package com.mck.coffee.model;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mck.coffee.model.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idOrder;
	private OrderStatus orderStatus;
	private Double totalPrice;

	@OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("order")
	private List<OrderItem> orderItems;

	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Payment payment;

	@ManyToOne
	@JoinColumn(name = "client_id")
	private User client;

	public Order() {

	}

	public Order(Long idOrder, OrderStatus orderStatus, Payment payment, Double totalPrice, List<OrderItem> items) {
		super();
		this.idOrder = idOrder;
		this.orderStatus = orderStatus;
		this.payment = payment;
		this.totalPrice = totalPrice;
		this.orderItems = items;
	}

	public Long getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(Long idOrder) {
		this.idOrder = idOrder;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> items) {
		this.orderItems = items;
	}

}