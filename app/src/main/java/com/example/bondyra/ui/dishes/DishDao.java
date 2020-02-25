package com.example.bondyra.ui.dishes;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.bondyra.ui.orders.Order;

import java.util.List;

@Dao
public interface DishDao {

    @Insert
    void insert(Dish dish);

    @Update
    void update(Dish dish);

    @Delete
    void delete(Dish dish);

    @Query("DELETE FROM dish_table")
    void deleteAllDishes();

    @Query("SELECT * FROM dish_table ORDER BY id")
    LiveData<List<Dish>> getAllDishes();
}
