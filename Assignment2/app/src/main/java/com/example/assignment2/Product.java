package com.example.assignment2;

public class Product {
    private int ProductID;
    private String name = null;
    private String description = null;
    private float price;

    public Product(int ProductID, String name, String description, float price) {
        setProductID(ProductID);
        setName(name);
        setDescription(description);
        setPrice(price);
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
