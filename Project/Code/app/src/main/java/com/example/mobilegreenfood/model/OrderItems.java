package com.example.mobilegreenfood.model;

public class OrderItems {
    private int order_item_id;
    private int order_id;
    private String order_item_name;
    private int order_item_price;
    private int order_item_quantity;

    public OrderItems() {
    }

    public OrderItems(int order_item_id, int order_id, String order_item_name, int order_item_price, int order_item_quantity) {
        this.order_item_id = order_item_id;
        this.order_id = order_id;
        this.order_item_name = order_item_name;
        this.order_item_price = order_item_price;
        this.order_item_quantity = order_item_quantity;
    }

    public int getOrder_item_id() {
        return order_item_id;
    }

    public void setOrder_item_id(int order_item_id) {
        this.order_item_id = order_item_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getOrder_item_name() {
        return order_item_name;
    }

    public void setOrder_item_name(String order_item_name) {
        this.order_item_name = order_item_name;
    }

    public int getOrder_item_price() {
        return order_item_price;
    }

    public void setOrder_item_price(int order_item_price) {
        this.order_item_price = order_item_price;
    }

    public int getOrder_item_quantity() {
        return order_item_quantity;
    }

    public void setOrder_item_quantity(int order_item_quantity) {
        this.order_item_quantity = order_item_quantity;
    }
}
