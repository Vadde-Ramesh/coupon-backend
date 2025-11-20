package com.coupon.Coupon.service;

import java.util.List;

import com.coupon.Coupon.entity.Cart;
import com.coupon.Coupon.entity.Coupon;
import com.coupon.Coupon.entity.User;

public interface CouponServiceIn {

	

	Coupon createCoupon(Coupon coupon);

	Coupon updateCoupon(Coupon coupon);

	List<Coupon> getAllCoupons();

	List<Coupon> getBestCouponsForUser(User user);

	List<Coupon> getBestCouponsForCart(Cart cart);

	Coupon getBestCoupon(User user, Cart cart);

}
