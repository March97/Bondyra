package com.example.bondyra.ui.dishes;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class DishViewModel extends AndroidViewModel{

    private DishRepository repository;
    private LiveData<List<Dish>> allDishes;

    public DishViewModel(@NonNull Application application) {
        super(application);
        repository = new DishRepository(application);
        allDishes = repository.getAllDishes();
    }

    public void insert(Dish dish) {
        repository.insert(dish);
    }
    public void update(Dish dish) {
        repository.update(dish);
    }
    public void delete(Dish dish) {
        repository.delete(dish);
    }
    public void deleteAllOrders(Dish dish) {
        repository.deleteAllOrders();
    }

    public LiveData<List<Dish>> getAllDishes() {
        return allDishes;
    }
}