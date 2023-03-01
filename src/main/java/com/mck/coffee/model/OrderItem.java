package com.mck.coffee.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_orderItems")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;
    
    private Integer quantity;
    private Double subPrice;

    @ManyToOne
    @JsonIgnoreProperties("orderItems")
    private Order order;

    @ManyToOne
    @JsonIgnoreProperties("orderItems")
    private Product product;
    
    
    public OrderItem() {
    	
    }

	public OrderItem(Long orderItemId, Integer quantity, Double subPrice, Order order, Product product) {
		super();
		this.orderItemId = orderItemId;
		this.quantity = quantity;
		this.subPrice = subPrice;
		this.order = order;
		this.product = product;
	}
	
	  public void calculateSubtotal() {
	        subPrice = quantity * product.getPrice();
	    }

	public Long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getSubPrice() {
		return subPrice;
	}

	public void setSubPrice(Double subPrice) {
		this.subPrice = subPrice;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

    
}
