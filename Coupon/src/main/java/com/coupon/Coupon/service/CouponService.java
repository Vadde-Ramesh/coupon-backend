package com.coupon.Coupon.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.coupon.Coupon.entity.Cart;
import com.coupon.Coupon.entity.Coupon;
import com.coupon.Coupon.entity.User;
import com.coupon.Coupon.repository.CouponRepository;

@Service
public class CouponService implements CouponServiceIn {
	
	private CouponRepository couponRepository;
	/**
	 * Constructor injection
	 */

	public CouponService(CouponRepository couponRepository) {
		super();
		this.couponRepository = couponRepository;
	}
	
	/**
	 * postCoupon method is used to save new Coupon DB
	 */
	@Override
	public Coupon postCoupon(Coupon coupon) {
		return couponRepository.save(coupon);
	}

	/**
	 * updateCoupon method is used to update a coupon in DB
	 */
	@Override
	public Coupon updateCoupon(Coupon coupon) {
		Coupon cop = new Coupon();
		cop.setCode(coupon.getCode());
		cop.setDesc(coupon.getDesc());
		cop.setDiscountType(coupon.getDiscountType());
		cop.setDiscountValue(coupon.getDiscountValue());
		cop.setEligibility(coupon.getEligibility());
		cop.setEnd(coupon.getEnd());
		cop.setId(coupon.getId());
		cop.setMaxDiscount(coupon.getMaxDiscount());
		cop.setStart(coupon.getStart());
		cop.setUsageLimit(coupon.getUsageLimit());
		return couponRepository.save(cop);
	}
	/**
	 * getAll method is used to get all the Coupons from DB
	 */

	@Override
	public List<Coupon> getAll() {
		return couponRepository.findAll();
	}

	/**
	 * getBestCoupons method is used to get best and matched coupons from DB
	 * based on user and cart
	 */
	@Override
	public List<Coupon> getBestCoupons(User user, Cart cart) {
		List<Coupon> coupons = couponRepository.findAll();
		
		return coupons.stream()
		        .filter(c -> CouponValidator.isEligible(c, user, cart))
		        .collect(Collectors.toList());

		
	}

	/**
	 * getBestUserCoupons method is used to get Best Coupons based on User Coupon
	 */
	@Override
	public List<Coupon> getBestUserCoupons(User user) {
		List<Coupon> coupons = couponRepository.findAll();
		
		return coupons.stream()
               .filter(c -> CouponValidator.userBasedEligible(c, user))
               .collect(Collectors.toList());
	}

	/**
	 * getBestCartCoupns method is used to get all the coupons based on Cart with coupons
	 */
	@Override
	public List<Coupon> getBestCartCoupons(Cart cart) {
        List<Coupon> coupons = couponRepository.findAll();
		
		return coupons.stream()
               .filter(c -> CouponValidator.cartBasedEligible(c, cart))
               .collect(Collectors.toList());
	}

}


























