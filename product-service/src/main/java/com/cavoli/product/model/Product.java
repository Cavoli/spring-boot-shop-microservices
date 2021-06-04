package com.cavoli.product.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long productId;

	@Size(max = 10, min = 1, message = "product name field must be equal or less than {max}")
	@Column(name = "product_name")
	private String productName;

	@Size(max = 5, min = 1, message = "product size field must be equal or less than {max}")
	@Column(name = "product_price")
	private String productPrice;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
}
