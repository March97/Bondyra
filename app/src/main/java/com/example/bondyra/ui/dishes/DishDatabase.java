package com.example.bondyra.ui.dishes;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.bondyra.ui.orders.Order;

@Database(entities = {Dish.class}, version = 1)
public abstract class DishDatabase extends RoomDatabase {

    private static DishDatabase instance;

    public abstract DishDao dishDao();

    public static synchronized DishDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    DishDatabase.class, "dish_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private DishDao dishDao;

        private PopulateDbAsyncTask(DishDatabase db) {
            dishDao = db.dishDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dishDao.insert(new Dish("Ryba po grecku", 10.0, 200));
            dishDao.insert(new Dish("Kotlet schabowy", 12.0, 300));
            dishDao.insert(new Dish("Kotlet mielony", 9.0, 350));
            return null;
        }
    }
}
