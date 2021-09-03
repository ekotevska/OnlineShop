package com.example.onlineshop.models;

public class Orders {

    private int id;
    private String price;
    private int image;
    private String size;

    public Orders(int id, String price, int image, String size) {
        this.id = id;
        this.price = price;
        this.image = image;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}

