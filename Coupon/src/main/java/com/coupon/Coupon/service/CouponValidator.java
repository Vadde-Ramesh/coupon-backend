package com.coupon.Coupon.service;

import java.time.LocalDate;

import com.coupon.Coupon.entity.Cart;
import com.coupon.Coupon.entity.Coupon;
import com.coupon.Coupon.entity.User;

public class CouponValidator {
	
	public static boolean userBasedEligible(Coupon coupon, User user) {

	    // 1. Coupon must be valid (not expired)
	    if (coupon.getEnd().isBefore(LocalDate.now())) {
	        System.out.println("Coupon " + coupon.getCode() + " not eligible: coupon expired on " 
	                + coupon.getEnd());
	        return false;
	    }

	    // 2. Eligibility type must match
	    if (!coupon.getEligibility().equals(user.getUserType())) {
	        System.out.println("Coupon " + coupon.getCode() + " not eligible: coupon eligibility " 
	                + coupon.getEligibility() + " does not match user type " + user.getUserType());
	        return false;
	    }

	    // 3. Lifetime spend check
	    if (user.getLifeTimeSpend() < coupon.getMinLifeTimeSpend()) {
	        System.out.println("Coupon " + coupon.getCode() + " not eligible: user lifetime spend " 
	                + user.getLifeTimeSpend() + " is less than required " + coupon.getMinLifeTimeSpend());
	        return false;
	    }

	    // 4. Minimum orders check
	    if (user.getOrderPlaced() < coupon.getMinOrdersPlaced()) {
	        System.out.println("Coupon " + coupon.getCode() + " not eligible: user orders placed " 
	                + user.getOrderPlaced() + " is less than required " + coupon.getMinOrdersPlaced());
	        return false;
	    }

	    // 5. Country type match
	    if (!coupon.getCountryType().equals(user.getCountryType())) {
	        System.out.println("Coupon " + coupon.getCode() + " not eligible: coupon country type " 
	                + coupon.getCountryType() + " does not match user country " + user.getCountryType());
	        return false;
	    }

	    // Eligible if all checks pass
	    return true;
	}


	
	public static boolean cartBasedEligible(Coupon coupon, Cart cart) {

	    // 1. Check minimum cart value
	    if (cart.getTotalSum() < coupon.getMinCartValue()) {
	        System.out.println("Coupon " + coupon.getCode() + " not eligible: cart total " 
	                + cart.getTotalSum() + " is less than minCartValue " + coupon.getMinCartValue());
	        return false;
	    }

	    // 2. Check if at least one product category matches coupon category
	    boolean categoryMatch = cart.getProducts().stream()
	            .map(p -> p.getProductCate().toString().toLowerCase())
	            .anyMatch(c -> c.equals(coupon.getProductCategory().toString().toLowerCase()));

	    if (!categoryMatch) {
	        System.out.println("Coupon " + coupon.getCode() + " not eligible: no products in cart match coupon category "
	                + coupon.getProductCategory());
	        return false;
	    }

	    // 3. Check minimum cart items count
	    int cartItemsCount = cart.getProducts().size();
	    if (cartItemsCount < coupon.getCartItems()) {
	        System.out.println("Coupon " + coupon.getCode() + " not eligible: cart has " 
	                + cartItemsCount + " items, required minimum is " + coupon.getCartItems());
	        return false;
	    }

	    // Eligible if all checks pass
	    return true;
	}



	public static boolean isEligible(Coupon coupon, User user, Cart cart) {
	    boolean userBased = CouponValidator.userBasedEligible(coupon, user);
	    boolean cartBased = CouponValidator.cartBasedEligible(coupon, cart);

	    if (!userBased) {
	        System.out.println("Coupon " + coupon.getCode() + " not eligible for user " + user.getId());
	    }

	    if (!cartBased) {
	        System.out.println("Coupon " + coupon.getCode() + " not eligible for cart");
	    }

	    return userBased && cartBased;
	}

}

