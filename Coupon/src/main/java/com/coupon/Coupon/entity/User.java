package com.coupon.Coupon.entity;

import com.coupon.Coupon.enms.CountryType;
import com.coupon.Coupon.enms.UserType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	private UserType userType;
	
	private CountryType countryType;
	
	private double lifeTimeSpend;
	
	private int orderPlaced;
	

}








