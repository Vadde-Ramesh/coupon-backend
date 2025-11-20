package com.coupon.Coupon.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coupon.Coupon.entity.Coupon;
import com.coupon.Coupon.entity.User;
import com.coupon.Coupon.entity.BestCouponRequest;
import com.coupon.Coupon.entity.Cart;
import com.coupon.Coupon.service.CouponServiceIn;

@RestController
@RequestMapping("/api/coupons")
public class CouponController {

    private final CouponServiceIn couponService;

    public CouponController(CouponServiceIn couponService) {
        this.couponService = couponService;
    }

    // -------------------------
    // Create a new Coupon
    // -------------------------
    @PostMapping("/add")
    public ResponseEntity<Coupon> createCoupon(@RequestBody Coupon coupon) {
        Coupon savedCoupon = couponService.createCoupon(coupon);
        return ResponseEntity.ok(savedCoupon);
    }

    // -------------------------
    // Update existing Coupon
    // -------------------------
    @PutMapping("/update")
    public ResponseEntity<Coupon> updateCoupon(@RequestBody Coupon coupon) {
        Coupon updatedCoupon = couponService.updateCoupon(coupon);
        return ResponseEntity.ok(updatedCoupon);
    }

    // -------------------------
    // Get all coupons
    // -------------------------
    @GetMapping("/all")
    public ResponseEntity<List<Coupon>> getAllCoupons() {
        List<Coupon> allCoupons = couponService.getAllCoupons();
        return ResponseEntity.ok(allCoupons);
    }

    // -------------------------
    // Get Best Coupon for User + Cart
    // -------------------------
    @PostMapping("/best")
    public ResponseEntity<Coupon> getBestCoupon(@RequestBody BestCouponRequest request) {
        Coupon bestCoupon = couponService.getBestCoupon(request.getUser(), request.getCart());
        return ResponseEntity.ok(bestCoupon);
        
    }

    // -------------------------
    // Get Best Coupon for User Only
    // -------------------------
    @PostMapping("/best/user")
    public ResponseEntity<List<Coupon>> getBestCouponsForUser(@RequestBody User user) {
        List<Coupon> coupons = couponService.getBestCouponsForUser(user);
        return ResponseEntity.ok(coupons);
    }

    // -------------------------
    // Get Best Coupon for Cart Only
    // -------------------------
    @PostMapping("/best/cart")
    public ResponseEntity<List<Coupon>> getBestCouponsForCart(@RequestBody Cart cart) {
        List<Coupon> coupons = couponService.getBestCouponsForCart(cart);
        return ResponseEntity.ok(coupons);
    }
}
