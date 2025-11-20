package com.coupon.Coupon.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.coupon.Coupon.entity.Coupon;
import com.coupon.Coupon.entity.Product;
import com.coupon.Coupon.entity.Cart;
import com.coupon.Coupon.entity.User;
import com.coupon.Coupon.repository.CouponRepository;

@Service
public class CouponService implements CouponServiceIn{

    private final CouponRepository couponRepository;

    public CouponService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    // -------------------------
    // Add new coupon
    // -------------------------
    public Coupon createCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    // -------------------------
    // Update coupon
    // -------------------------
    public Coupon updateCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    // -------------------------
    // Get all coupons
    // -------------------------
    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    // -------------------------
    // Get Best Coupon for User + Cart
    // -------------------------
    public Coupon getBestCoupon(User user, Cart cart) {
        List<Coupon> eligibleCoupons = couponRepository.findAll()
                .stream()
                .filter(c -> isCouponValid(c, user, cart))
                .collect(Collectors.toList());

        if (eligibleCoupons.isEmpty()) return null;

        return eligibleCoupons.stream()
                .sorted((a, b) -> compareCoupons(a, b, cart))
                .findFirst()
                .orElse(null);
    }

    // -------------------------
    // Best coupons for user only
    // -------------------------
    public List<Coupon> getBestCouponsForUser(User user) {
        return couponRepository.findAll()
                .stream()
                .filter(c -> isUserEligible(c, user))
                .collect(Collectors.toList());
    }

    // -------------------------
    // Best coupons for cart only
    // -------------------------
    public List<Coupon> getBestCouponsForCart(Cart cart) {
        return couponRepository.findAll()
                .stream()
                .filter(c -> isCartEligible(c, cart))
                .collect(Collectors.toList());
    }

    // -------------------------
    // Validation helpers
    // -------------------------
    private boolean isCouponValid(Coupon coupon, User user, Cart cart) {
        return isDateValid(coupon) && isUserEligible(coupon, user) && isCartEligible(coupon, cart);
    }

    private boolean isDateValid(Coupon coupon) {
        LocalDate today = LocalDate.now();
        return !today.isBefore(coupon.getStartDate()) && !today.isAfter(coupon.getEndDate());
    }

    private boolean isUserEligible(Coupon coupon, User user) {
        // 1. Check user tier
        if (coupon.getAllowedUserTiers() != null && !coupon.getAllowedUserTiers().contains(user.getUserTier())) {
            System.out.println("Rejected: user tier " + user.getUserTier() + " not in allowed tiers " + coupon.getAllowedUserTiers());
            return false;
        }

        // 2. Check lifetime spend
        if (coupon.getMinLifetimeSpend() != null && user.getLifeTimeSpend() < coupon.getMinLifetimeSpend()) {
            System.out.println("Rejected: lifetime spend " + user.getLifeTimeSpend() + " < " + coupon.getMinLifetimeSpend());
            return false;
        }

        // 3. Check minimum orders placed
        if (coupon.getMinOrdersPlaced() != null && user.getTotalOrders() < coupon.getMinOrdersPlaced()) {
            System.out.println("Rejected: total orders " + user.getTotalOrders() + " < " + coupon.getMinOrdersPlaced());
            return false;
        }

        // 4. First order only restriction
        if (coupon.getFirstOrderOnly() != null && coupon.getFirstOrderOnly() && user.getTotalOrders() > 0) {
            System.out.println("Rejected: firstOrderOnly is true but user already has orders " + user.getTotalOrders());
            return false;
        }

        // 5. Country restriction
        if (coupon.getAllowedCountries() != null && !coupon.getAllowedCountries().contains(user.getCountry())) {
            System.out.println("Rejected: country " + user.getCountry() + " not in allowed countries " + coupon.getAllowedCountries());
            return false;
        }

        // 6. Passed all checks
        return true;
    }

    private boolean isCartEligible(Coupon coupon, Cart cart) {
        // 1. Null-safe check for cart and products
        if (cart == null || cart.getProducts() == null || cart.getProducts().isEmpty()) {
            System.out.println("Cart is empty or null");
            return false;
        }

        // 2. Minimum cart value
        if (coupon.getMinCartValue() != null && cart.getTotalAmount() < coupon.getMinCartValue()) {
            System.out.println("Cart total " + cart.getTotalAmount() + " < minCartValue " + coupon.getMinCartValue());
            return false;
        }

        // 3. Minimum items count
        if (coupon.getMinItemsCount() != null && cart.getTotalItems() < coupon.getMinItemsCount()) {
            System.out.println("Cart items " + cart.getTotalItems() + " < minItemsCount " + coupon.getMinItemsCount());
            return false;
        }

     // 4. Applicable categories
        if (coupon.getApplicableCategories() != null && !coupon.getApplicableCategories().isEmpty()) {

            List<String> allowedCategories = coupon.getApplicableCategories().stream()
                    .map(Enum::toString)
                    .collect(Collectors.toList());

            List<String> cartCategories = cart.getProducts().stream()
                    .map(Product::getCategory)
                    .filter(Objects::nonNull)
                    .map(Enum::toString)
                    .collect(Collectors.toList());

            boolean matchFound = allowedCategories.stream()
                    .anyMatch(cartCategories::contains);

            if (!matchFound) {
                System.out.println("Cart does not contain applicable categories " + allowedCategories);
                return false;
            }
        }



        // 5. Excluded categories
        if (coupon.getExcludedCategories() != null && !coupon.getExcludedCategories().isEmpty()) {
            List<String> excludedCategories = coupon.getExcludedCategories().stream()
                    .map(Enum::toString)
                    .collect(Collectors.toList());

            List<String> cartCategories = cart.getProducts().stream()
                    .map(Product::getCategory)
                    .filter(Objects::nonNull)
                    .map(Enum::toString)
                    .collect(Collectors.toList());

            // Check if any excluded category is present
            excludedCategories.retainAll(cartCategories);
            if (!excludedCategories.isEmpty()) {
                System.out.println("Cart contains excluded categories: " + excludedCategories);
                return false;
            }
        }

        // 6. Passed all checks
        return true;
    }



    // -------------------------
    // Compare coupons (best selection)
    // -------------------------
    private int compareCoupons(Coupon a, Coupon b, Cart cart) {
        double discountA = calculateDiscount(a, cart);
        double discountB = calculateDiscount(b, cart);
        if (discountA != discountB) return Double.compare(discountB, discountA);
        int dateCompare = a.getEndDate().compareTo(b.getEndDate());
        if (dateCompare != 0) return dateCompare;
        return a.getCode().compareTo(b.getCode());
    }

    private double calculateDiscount(Coupon coupon, Cart cart) {
        double cartValue = cart.getTotalAmount();
        if (coupon.getDiscountType() != null && coupon.getDiscountType().name().equals("FLAT")) {
            return coupon.getDiscountValue();
        } else {
            double discount = cartValue * coupon.getDiscountValue() / 100;
            if (coupon.getMaxDiscountAmount() != null) return Math.min(discount, coupon.getMaxDiscountAmount());
            return discount;
        }
    }
}

























