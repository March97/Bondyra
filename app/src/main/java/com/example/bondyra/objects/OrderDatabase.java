package com.example.bondyra.objects;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Order.class}, version = 1)
public abstract class OrderDatabase extends RoomDatabase {

    private static OrderDatabase instance;

    public abstract OrderDao orderDao();

    public static synchronized OrderDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    OrderDatabase.class, "order_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private OrderDao orderDao;

        private PopulateDbAsyncTask(OrderDatabase db) {
            orderDao = db.orderDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            orderDao.insert(new Order("Dishes 1", "12.00.00", 1, 1.0, "Active"));
            orderDao.insert(new Order("Dishes 2", "12.00.00", 1, 1.0, "Active"));
            orderDao.insert(new Order("Dishes 3", "12.00.00", 1, 1.0, "Active"));
            return null;
        }
    }
}
