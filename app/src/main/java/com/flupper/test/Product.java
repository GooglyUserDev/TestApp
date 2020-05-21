package com.flupper.test;


public class Product {
    private int id = 0;
    private String name = "";
    private String description = "";
    private String product_photo = "";
    private String color = "";
    private double regular_price = 0;
    private double sale_price = 0;
    public Product(int id, String name, String description, double regular_price, double sale_price, String product_photo, String color) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.regular_price = regular_price;
        this.sale_price = sale_price;
        this.product_photo = product_photo;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRegularPrice() {
        return regular_price;
    }

    public void setRegularPrice(double regular_price) {
        this.regular_price = regular_price;
    }

    public double getSalePrice() {
        return sale_price;
    }

    public void setSalePrice(double sale_price) {
        this.sale_price = sale_price;
    }

    public String getProductPhoto() {
        return product_photo;
    }

    public void setProductPhoto(String product_photo) {
        this.product_photo = product_photo;
    }
}
