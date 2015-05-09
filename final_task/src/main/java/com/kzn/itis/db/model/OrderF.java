package com.kzn.itis.db.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class OrderF {

	public OrderF() {
	}

	public OrderF(int order_id, int user_id, String brand, int price) {
		if (order_id < 0 || user_id < 0 || brand == null || price < 0) {
			this.order_id = 0;
			this.user_id = 0;
			this.brand = "error_brand";
			this.price = 0;
		} else {
			this.order_id = order_id;
			this.user_id = user_id;
			this.brand = brand;
			this.price = price;
		}
	}

	private int order_id;

	@Column(name = "order_id")
	@Basic
	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	private int user_id;

	@Column(name = "user_id")
	@Basic
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	private String brand;

	@Column(name = "brand")
	@Basic
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	private int price;

	@Column(name = "price")
	@Basic
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		OrderF event = (OrderF) o;

		if (order_id != event.order_id)
			return false;
		if (user_id != event.user_id)
			return false;
		if (brand != null ? !brand.equals(event.brand) : event.brand != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = order_id;
		result = 31 * result + user_id;
		result = 31 * result + (brand != null ? brand.hashCode() : 0);
		result = 31 * result + price;
		return result;
	}

}
/*
 * "order_id INTEGER not NULL AUTO_INCREMENT, user_id INTEGER not NULL," +
 * "price INTEGER NOT NULL," + "brand VARCHAR(256)," + "PRIMARY KEY(order_id))";
 */