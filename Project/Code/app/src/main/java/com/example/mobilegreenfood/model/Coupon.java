package com.example.mobilegreenfood.model;

public class Coupon {
    private int coupon_id;
    private String coupon_code;
    private int coupon_percent_discount;
    private int coupon_count;

    public Coupon(int coupon_id, String coupon_code, int coupon_percent_discount, int coupon_count) {
        this.coupon_id = coupon_id;
        this.coupon_code = coupon_code;
        this.coupon_percent_discount = coupon_percent_discount;
        this.coupon_count = coupon_count;
    }

    public int getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(int coupon_id) {
        this.coupon_id = coupon_id;
    }

    public String getCoupon_code() {
        return coupon_code;
    }

    public void setCoupon_code(String coupon_code) {
        this.coupon_code = coupon_code;
    }

    public int getCoupon_percent_discount() {
        return coupon_percent_discount;
    }

    public void setCoupon_percent_discount(int coupon_percent_discount) {
        this.coupon_percent_discount = coupon_percent_discount;
    }

    public int getCoupon_count() {
        return coupon_count;
    }

    public void setCoupon_count(int coupon_count) {
        this.coupon_count = coupon_count;
    }
}
