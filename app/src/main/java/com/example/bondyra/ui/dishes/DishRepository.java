package com.example.bondyra.ui.dishes;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.bondyra.ui.orders.Order;

import java.util.ArrayList;
import java.util.List;

public class DishRepository {
    private DishDao dishDao;
    private LiveData<List<Dish>> allDishes;

    public DishRepository(Application application) {
        DishDatabase database = DishDatabase.getInstance(application);
        dishDao = database.dishDao();
        allDishes = dishDao.getAllDishes();
    }

    public void insert(Dish dish) {
        new InsertOrderAsyncTask(dishDao).execute(dish);
    }

    public void update(Dish dish) {
        new UpdateOrderAsyncTask(dishDao).execute(dish);

    }

    public void delete(Dish dish) {
        new DeleteOrderAsyncTask(dishDao).execute(dish);
    }

    public void deleteAllOrders() {
        new DeleteAllOrdersAsyncTask(dishDao).execute();
    }

    public LiveData<List<Dish>> getAllDishes() {
        return allDishes;
    }

    private static class InsertOrderAsyncTask extends AsyncTask<Dish, Void, Void> {
        private DishDao dishDao;
        private InsertOrderAsyncTask(DishDao dishDao) {
            this.dishDao = dishDao;
        }

        @Override
        protected Void doInBackground(Dish... dishes) {
            dishDao.insert(dishes[0]);
            return null;
        }
    }

    private static class UpdateOrderAsyncTask extends AsyncTask<Dish, Void, Void> {
        private DishDao dishDao;
        private UpdateOrderAsyncTask(DishDao dishDao) {
            this.dishDao = dishDao;
        }

        @Override
        protected Void doInBackground(Dish... dishes) {
            dishDao.update(dishes[0]);
            return null;
        }
    }

    private static class DeleteOrderAsyncTask extends AsyncTask<Dish, Void, Void> {
        private DishDao dishDao;
        private DeleteOrderAsyncTask(DishDao dishDao) {
            this.dishDao = dishDao;
        }

        @Override
        protected Void doInBackground(Dish... dishes) {
            dishDao.delete(dishes[0]);
            return null;
        }
    }

    private static class DeleteAllOrdersAsyncTask extends AsyncTask<Void, Void, Void> {
        private DishDao dishDao;
        private DeleteAllOrdersAsyncTask(DishDao dishDao) {
            this.dishDao = dishDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dishDao.deleteAllDishes();
            return null;
        }
    }
}
