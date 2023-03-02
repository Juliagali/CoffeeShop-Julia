package com.mck.coffee.model;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_payment")
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String paymentMethod;

	@JsonIgnore
	@OneToOne
	@MapsId
	private Order order;

	private double amount;

	public Payment() {

	}

	public Payment(Long id , Order order, String paymentMethod) {
		super();
		this.id = id;
		this.order = order;
		this.paymentMethod = paymentMethod;
		if (order != null) {
			this.amount = calculateAmount();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getPaymentType() {
		return paymentMethod;
	}

	public void setPaymentType(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	private double calculateAmount() {
		if (this.order != null) {
			double totalPrice = this.order.getTotalPrice();
			if (this.paymentMethod.equals("creditCard")) {
				return totalPrice * 10.02;
			} else {
				return totalPrice;
			}
		}
		return 0;
	}
}
