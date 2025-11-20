package com.coupon.Coupon.controller;

import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * 
     *  Constructor injection
     */
    public CouponController(CouponServiceIn couponService) {
        this.couponService = couponService;
    }

    /**
     * 
     * @RequestBody Takes coupon as Argument and saves coupon into DB
     * @return saved Coupon
     */
    @PostMapping("/add")
    public ResponseEntity<Coupon> createCoupon(@RequestBody Coupon coupon) {
        Coupon savedCoupon = couponService.createCoupon(coupon);
        return ResponseEntity.ok(savedCoupon);
    }

    /**
     * 
     * @RequestBody Takes Coupon as argument 
     * @return Updated coupon
     */
    @PutMapping("/update")
    public ResponseEntity<Coupon> updateCoupon(@RequestBody Coupon coupon) {
        Coupon updatedCoupon = couponService.updateCoupon(coupon);
        return ResponseEntity.ok(updatedCoupon);
    }

    /**
     * No input
     * @return return all the coupons from DB
     */
    @GetMapping("/all")
    public ResponseEntity<List<Coupon>> getAllCoupons() {
        List<Coupon> allCoupons = couponService.getAllCoupons();
        return ResponseEntity.ok(allCoupons);
    }

    /**
     * Get Best Coupon for User + Cart
     * @param BestCouponRequest as request bzc Spring not allows 2 RequestBodys at a time
     * @return best Coupons valid for both user and cart
     */
    @PostMapping("/best")
    public ResponseEntity<Coupon> getBestCoupon(@RequestBody BestCouponRequest request) {
        Coupon bestCoupon = couponService.getBestCoupon(request.getUser(), request.getCart());
        return ResponseEntity.ok(bestCoupon);
        
    }

    /**
     * Get Best Coupon for User Only
     * @RequestBody Takes  user input
     * @return best Coupons based on user
     */
    @PostMapping("/best/user")
    public ResponseEntity<List<Coupon>> getBestCouponsForUser(@RequestBody User user) {
        List<Coupon> coupons = couponService.getBestCouponsForUser(user);
        return ResponseEntity.ok(coupons.stream()
                .sorted((c1, c2) -> Double.compare(c2.getDiscountValue(), c1.getDiscountValue()))
                .collect(Collectors.toList()));
    }
    /**
     * Get Best Coupon for User Only
     * @RequestBody Takes  Cart input
     * @return best Coupons based on Cart
     */
    @PostMapping("/best/cart")
    public ResponseEntity<List<Coupon>> getBestCouponsForCart(@RequestBody Cart cart) {
        List<Coupon> coupons = couponService.getBestCouponsForCart(cart);
        return ResponseEntity.ok(coupons.stream()
                .sorted((c1, c2) -> Double.compare(c2.getDiscountValue(), c1.getDiscountValue()))
                .collect(Collectors.toList()));
    }
}
