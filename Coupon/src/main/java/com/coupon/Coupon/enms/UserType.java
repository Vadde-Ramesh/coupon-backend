package com.coupon.Coupon.enms;

public enum UserType {
	NEW, REGULAR, GOLD;

	public boolean isEmpty() {
		return false;
	}

	public static boolean contains(UserType type) {
        for (UserType t : values()) {
            if (t == type) return true;
        }
        return false;
    }
}
