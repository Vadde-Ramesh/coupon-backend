package com.coupon.Coupon.entity;

import com.coupon.Coupon.enms.CountryType;
import com.coupon.Coupon.enms.UserType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true, nullable = false)
    private String name;

    private UserType userTier; // NEW, REGULAR, GOLD

    private CountryType country; // IN, US, etc.

    private double lifeTimeSpend;

    private int totalOrders;
}