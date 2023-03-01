package com.mck.coffee.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	
	private String name;
	private String description;
	private Double price;
	private String imgUrl;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category categories;	

	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    @JsonIgnoreProperties("product")
	private List<OrderItem> orderItems;


	
	public Product() {
	}

	public Product(Long productId, String name, String description, Double price, String imgUrl, Category categories) {
		super();
		this.productId = productId;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
		this.categories = categories;
	}

	public Long getProductId() {
		return productId;
	}

	public void setId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	

	public Category getCategory() {
		return categories;
	}

	public void setCategory(Category categories) {
		this.categories = categories;
	}

}