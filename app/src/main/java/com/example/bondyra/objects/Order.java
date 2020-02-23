package com.example.bondyra.objects;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Time;
import java.util.ArrayList;

@Entity(tableName = "order_table")
public class Order {

    @PrimaryKey(autoGenerate = true)
    private int id;

    //private ArrayList<Dish> dishes;
    //private Time timeOfPlaced;

    private String dishes;
    private String timeOfPlaced;
    private int numberOfTable;
    private double cost;
    private String status;

    public Order(String dishes, String timeOfPlaced, int numberOfTable, double cost, String status) {
        this.dishes = dishes;
        this.timeOfPlaced = timeOfPlaced;
        this.numberOfTable = numberOfTable;
        this.cost = cost;
        this.status = status;
    }
//    public Order(ArrayList<Dish> dishes, Time timeOfPlaced, int numberOfTable, float cost, String status) {
//        this.dishes = dishes;
//        this.timeOfPlaced = timeOfPlaced;
//        this.numberOfTable = numberOfTable;
//        this.cost = cost;
//        this.status = status;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public ArrayList<Dish> getDishes() {
//        return dishes;
//    }
//
//    public void setDishes(ArrayList<Dish> dishes) {
//        this.dishes = dishes;
//    }
//
//    public Time getTimeOfPlaced() {
//        return timeOfPlaced;
//    }
//
//    public void setTimeOfPlaced(Time timeOfPlaced) {
//        this.timeOfPlaced = timeOfPlaced;
//    }

    public String getDishes() {
        return dishes;
    }

    public void setDishes(String dishes) {
        this.dishes = dishes;
    }

    public String getTimeOfPlaced() {
        return timeOfPlaced;
    }

    public void setTimeOfPlaced(String timeOfPlaced) {
        this.timeOfPlaced = timeOfPlaced;
    }

    public int getNumberOfTable() {
        return numberOfTable;
    }

    public void setNumberOfTable(int numberOfTable) {
        this.numberOfTable = numberOfTable;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
