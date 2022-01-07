package com.example.mobilegreenfood.model;

import java.io.Serializable;

public class Order implements Serializable {
    private int order_id;
    private int user_id;
    private int coupon_id = 0;
    private double amount;
    private int order_status;
    private String order_note;
    private String coupon_code;
    private int coupon_percent_discount = 0;
    private int coupon_count;

    public Order() {
    }

    public Order(int order_id, int user_id, int coupon_id, int amount, int order_status, String order_note) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.coupon_id = coupon_id;
        this.amount = amount;
        this.order_status = order_status;
        this.order_note = order_note;
    }

    public Order(int order_id, int user_id, int coupon_id, int amount, int order_status, String order_note, String coupon_code, int coupon_percent_discount, int coupon_count) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.coupon_id = coupon_id;
        this.amount = amount;
        this.order_status = order_status;
        this.order_note = order_note;
        this.coupon_code = coupon_code;
        this.coupon_percent_discount = coupon_percent_discount;
        this.coupon_count = coupon_count;
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

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(int coupon_id) {
        this.coupon_id = coupon_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }

    public String getOrder_note() {
        return order_note;
    }

    public void setOrder_note(String order_note) {
        this.order_note = order_note;
    }
}
