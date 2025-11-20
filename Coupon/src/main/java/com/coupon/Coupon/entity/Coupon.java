package com.coupon.Coupon.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.coupon.Coupon.enms.DiscountType;
import com.coupon.Coupon.enms.ProductCategory;
import com.coupon.Coupon.enms.UserType;
import com.coupon.Coupon.enms.CountryType;

@Entity
@Table(name = "coupons")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true, nullable = false)
    private String code;

    private String description;

    @Enumerated(EnumType.STRING)
    private DiscountType discountType;   // FLAT or PERCENT

    private double discountValue;        // 100 for flat, 10 for percent

    private Double maxDiscountAmount;    // optional, only for percent

    // ---------- Validity Window ----------
    private LocalDate startDate;
    private LocalDate endDate;

    // ---------- Usage Limit ----------
    private Integer usageLimitPerUser;   // nullable means unlimited

    // ---------- USER Based Eligibility ---------
    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "coupon_user_tiers", joinColumns = @JoinColumn(name = "coupon_id"))
    @Column(name = "user_tier")
    private List<UserType> allowedUserTiers;

    private Double minLifetimeSpend;
    private Integer minOrdersPlaced;

    private Boolean firstOrderOnly;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "coupon_allowed_countries", joinColumns = @JoinColumn(name = "coupon_id"))
    @Column(name = "country")
    private List<CountryType> allowedCountries;

    // ---------- CART Based Eligibility ----------
    private Double minCartValue;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "coupon_applicable_categories", joinColumns = @JoinColumn(name = "coupon_id"))
    @Column(name = "category")
    private List<ProductCategory> applicableCategories;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "coupon_excluded_categories", joinColumns = @JoinColumn(name = "coupon_id"))
    @Column(name = "category")
    private List<ProductCategory> excludedCategories;

    private Integer minItemsCount;
}
