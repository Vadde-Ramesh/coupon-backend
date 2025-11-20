package com.coupon.Coupon.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "carts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> products;

    // Helper methods for cart-based calculations
    public double getTotalAmount() {
        return products.stream().mapToDouble(p -> p.getUnitPrice() * p.getQuantity()).sum();
    }

    public int getTotalItems() {
        return products.stream().mapToInt(Product::getQuantity).sum();
    }
}