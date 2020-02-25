package com.example.bondyra.ui.dishes;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dish_table")
public class Dish {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private double price;
    private int mass;

    public Dish(String name, double price, int mass) {
        this.name = name;
        this.price = price;
        this.mass = mass;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

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
