package com.coupon.Coupon.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coupon.Coupon.entity.Cart;
import com.coupon.Coupon.entity.Coupon;
import com.coupon.Coupon.entity.User;
import com.coupon.Coupon.service.CouponServiceIn;

@RestController
@RequestMapping("/coupon")
public class CouponController {
	
	private CouponServiceIn couponSer;
	/**
	 * Constructor Injection
	 */
	public CouponController(CouponServiceIn couponSer) {
		this.couponSer = couponSer;
	}
	/**
	 * 
	 * by using @PostMapping Method with @RequestBody Coupon and @path "/addCoupon"
	 * Add Coupon to Database
	 * @return saved Coupon 
	 */
	
	@PostMapping("/addCoupon")
	public ResponseEntity<? > postCoupon(@RequestBody Coupon coupon){
		Coupon cop = couponSer.postCoupon(coupon);
		return ResponseEntity.ok(cop);
	}
	/**
	 * @PutMapping Method with @requestBody coupon to Update exist Coupon
	 * @Path /updateCoupon
	 * @return updated coupon with updated in database.
	 */
	@PutMapping("/updateCoupon")
	public ResponseEntity<? > updateCoupon(@RequestBody Coupon coupon){
		Coupon cop = couponSer.updateCoupon(coupon);
		return ResponseEntity.ok(cop);
	}
	/**
	 * @GetMapping Method to get all the coupons in the database
	 * @Path /all
	 * @return  List of Coupons
	 * and ? shows that this symbol can returns any DataType (List<Coupon>)
	 */
	@GetMapping("/all")
	public ResponseEntity<? > getAllCoupons(){
		List<Coupon> cop = couponSer.getAll();
		return ResponseEntity.ok(cop);
	}
	/**
	 * @GetMapping with @path /bestCoupons
	 * @RequestBody of User and Cart as arguments to get Best Coupons from DB
	 * @return List of Coupons that sorted from DB or sends No coupons found
	 */
	
	@GetMapping("/bestCoupons")
	public ResponseEntity<? > getBestCoupon(@RequestBody User user, @RequestBody Cart cart){
		List<Coupon> cop = couponSer.getBestCoupons(user, cart);
		if(cop.size()>0) return ResponseEntity.ok(cop);
		else return ResponseEntity.ok("Coupons not Found");
	}
	/**
	 * @GetMapping with @path /bestUser
	 * @RequestBody of User as arguments to get Best Coupons from DB
	 * @return List of Coupons that sorted from DB or sends No coupons found
	 */
	
	@GetMapping("/bestUser")
	public ResponseEntity<? > getBestUserCoupon(@RequestBody User user){
		List<Coupon> cop = couponSer.getBestUserCoupons(user);
		if(cop.size()>0) return ResponseEntity.ok(cop);
		else return ResponseEntity.ok("Coupons not Found");
	}
	/**
	 * @GetMapping with @path /bestCart
	 * @RequestBody of Cart as arguments to get Best Coupons from DB
	 * @return List of Coupons that sorted from DB or sends No coupons found
	 */
	
	@GetMapping("/bestCart")
	public ResponseEntity<? > getBestCartCoupon(@RequestBody Cart cart){
		List<Coupon> cop = couponSer.getBestCartCoupons(cart);
		if(cop.size()>0) return ResponseEntity.ok(cop);
		else return ResponseEntity.ok("Coupons not Found");
	}
	

}
