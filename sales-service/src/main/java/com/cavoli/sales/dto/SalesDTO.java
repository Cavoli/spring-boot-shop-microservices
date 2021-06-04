package com.cavoli.sales.dto;

import java.util.Date;

public class SalesDTO {

	private Long saleId;

	private String price;

	private Date created = new Date();

	private Date updated = new Date();

	private String product;

	private String user;

	public Long getSaleId() {
		return saleId;
	}

	public void setSaleId(Long saleId) {
		this.saleId = saleId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getProducts() {
		return product;
	}

	public void setProducts(String product) {
		this.product = product;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}