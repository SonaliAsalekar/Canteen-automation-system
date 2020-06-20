package com.example.demo;

public class iteminfo {
    private  String itemname;

    public  String quantity;
    public String price;
    public String category;

    public iteminfo() {
    }

    public String getItemname() {
        return itemname;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public iteminfo(String itemname, String quantity, String price, String category) {
        this.itemname = itemname;
        this.quantity = quantity;
        this.price = price;
        this.category = category;

    }
}
