package com.example.bondyra.objects;

public class Dish {

    private String name;
    private double price;
    private int mass;

    public Dish(String name, double price, int mass) {
        this.name = name;
        this.price = price;
        this.mass = mass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMass() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }
}
