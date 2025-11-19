package com.coupon.Coupon.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	@OneToMany
	private List<Product> products = new ArrayList<>();
	
	private int numberItems;

	public List<Product> getItems() {
		return this.products;
	}

	public double getTotalSum() {
		return products.stream().mapToDouble(Product::getPrice).sum();
	}

	
}







