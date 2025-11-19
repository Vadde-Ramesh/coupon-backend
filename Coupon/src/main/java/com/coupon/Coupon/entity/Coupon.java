package com.coupon.Coupon.entity;

import java.time.LocalDate;

import com.coupon.Coupon.enms.CountryType;
import com.coupon.Coupon.enms.DiscountType;
import com.coupon.Coupon.enms.ProductCategory;
import com.coupon.Coupon.enms.UserType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	private String code;
	@Column(name = "description")
	private String desc;
	
	private DiscountType discountType;
	
	private double discountValue;
	
	private double maxDiscount;
	
	private double minLifeTimeSpend;
	
	private int minOrdersPlaced;
	
	@Column(name = "start_date")
	private LocalDate start;

	@Column(name = "end_date")
	private LocalDate end;
	
	private int usageLimit;
	
	private UserType eligibility;
	
	private CountryType countryType;
	
	private double minCartValue;
	
	private ProductCategory productCategory;
	
	private Long cartItems;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public DiscountType getDiscountType() {
		return discountType;
	}

	public void setDiscountType(DiscountType discountType) {
		this.discountType = discountType;
	}

	public double getDiscountValue() {
		
		return discountValue;
	}

	public void setDiscountValue(double discountValue) {
		this.discountValue = discountValue;
	}

	public double getMaxDiscount() {
		return maxDiscount;
	}

	public void setMaxDiscount(double maxDiscount) {
		this.maxDiscount = maxDiscount;
	}

	public double getMinLifeTimeSpend() {
		return minLifeTimeSpend;
	}

	public void setMinLifeTimeSpend(double minLifeTimeSpend) {
		this.minLifeTimeSpend = minLifeTimeSpend;
	}

	public int getMinOrdersPlaced() {
		return minOrdersPlaced;
	}

	public void setMinOrdersPlaced(int minOrdersPlaced) {
		this.minOrdersPlaced = minOrdersPlaced;
	}

	public LocalDate getStart() {
		return start;
	}

	public void setStart(LocalDate start) {
		this.start = start;
	}

	public LocalDate getEnd() {
		return end;
	}

	public void setEnd(LocalDate end) {
		this.end = end;
	}

	public int getUsageLimit() {
		return usageLimit;
	}

	public void setUsageLimit(int usageLimit) {
		this.usageLimit = usageLimit;
	}

	public UserType getEligibility() {
		return eligibility;
	}

	public void setEligibility(UserType eligibility) {
		this.eligibility = eligibility;
	}

	public CountryType getCountryType() {
		return countryType;
	}

	public void setCountryType(CountryType countryType) {
		this.countryType = countryType;
	}

	public double getMinCartValue() {
		return minCartValue;
	}

	public void setMinCartValue(double minCartValue) {
		this.minCartValue = minCartValue;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public Long getCartItems() {
		return cartItems;
	}

	public void setCartItems(Long cartItems) {
		this.cartItems = cartItems;
	}

}

















