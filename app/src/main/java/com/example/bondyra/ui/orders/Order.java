package com.example.bondyra.ui.orders;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

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
    private int status;

    @Ignore
    public Order(String dishes, int numberOfTable, int status) {
        this.dishes = dishes;
        this.numberOfTable = numberOfTable;

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        this.timeOfPlaced = formatter.format(date);
        this.cost = 12.0;
        this.status = status;
    }

    @Ignore
    public Order(String dishes, int numberOfTable, int status, double cost) {
        this.dishes = dishes;
        this.numberOfTable = numberOfTable;
        this.cost = cost;
        this.status = status;

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        this.timeOfPlaced = formatter.format(date);
    }

    public Order(String dishes, String timeOfPlaced, int numberOfTable, double cost, int status) {
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

    public void setCost(double cost) { this.cost = cost; }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
