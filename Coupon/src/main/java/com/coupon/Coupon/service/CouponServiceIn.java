package com.coupon.Coupon.service;

import java.util.List;

import com.coupon.Coupon.entity.Cart;
import com.coupon.Coupon.entity.Coupon;
import com.coupon.Coupon.entity.User;

public interface CouponServiceIn {

	
	Coupon postCoupon(Coupon coupon);

	Coupon updateCoupon(Coupon coupon);

	List<Coupon> getAll();

	List<Coupon> getBestCoupons(User user, Cart cart);

	List<Coupon> getBestUserCoupons(User user);

	List<Coupon> getBestCartCoupons(Cart cart);

}
