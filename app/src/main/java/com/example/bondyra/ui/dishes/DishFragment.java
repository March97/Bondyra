package com.example.bondyra.ui.dishes;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bondyra.R;
import com.example.bondyra.ui.orders.Order;
import com.example.bondyra.ui.orders.OrderAdapter;
import com.example.bondyra.ui.orders.OrdersViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static android.app.Activity.RESULT_OK;

public class DishFragment extends Fragment {

    public static final int ADD_DISH_REQUEST = 1;
    public static final int EDIT_DISH_REQUEST = 2;

    private DishViewModel dishViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dish, container, false);
        FloatingActionButton buttonAddOrder = root.findViewById(R.id.dish_fab);
        buttonAddOrder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddDishActivity.class);
                startActivityForResult(intent, ADD_DISH_REQUEST);
            }
        });

        RecyclerView recyclerView = root.findViewById(R.id.dish_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        final DishAdapter adapter = new DishAdapter();
        recyclerView.setAdapter(adapter);

        dishViewModel = ViewModelProviders.of(this).get(DishViewModel.class);
        dishViewModel.getAllDishes().observe(this, new Observer<List<Dish>>() {
            @Override
            public void onChanged(List<Dish> dishes) {
                adapter.setDishes(dishes);
                Toast.makeText(getContext(), "onChanged", Toast.LENGTH_SHORT).show();
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                dishViewModel.delete(adapter.getDishAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getContext(), "Dish deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new DishAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Dish dish) {
                Intent intent = new Intent(getContext(), AddDishActivity.class);
                intent.putExtra(AddDishActivity.EXTRA_ID, dish.getId());
                intent.putExtra(AddDishActivity.EXTRA_NAME, dish.getName());
                intent.putExtra(AddDishActivity.EXTRA_PRICE, dish.getPrice());
                intent.putExtra(AddDishActivity.EXTRA_MASS, dish.getMass());
                startActivityForResult(intent, EDIT_DISH_REQUEST);
            }
        });

        return root;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_DISH_REQUEST && resultCode == RESULT_OK) {
            String name = data.getStringExtra(AddDishActivity.EXTRA_NAME);
            int mass = data.getIntExtra(AddDishActivity.EXTRA_MASS, 1);
            double price = data.getDoubleExtra(AddDishActivity.EXTRA_PRICE, 1);

            Dish dish = new Dish(name, price, mass);
            dishViewModel.insert(dish);
            Toast.makeText(getContext(), "Dish saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_DISH_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddDishActivity.EXTRA_ID, -1);

            if (id == -1) {
                Toast.makeText(getContext(), "Dish can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String name = data.getStringExtra(AddDishActivity.EXTRA_NAME);
            int mass = data.getIntExtra(AddDishActivity.EXTRA_MASS, 1);
            double price = data.getDoubleExtra(AddDishActivity.EXTRA_PRICE, 1);

            Dish dish = new Dish(name, price, mass);
            dish.setId(id);
            dishViewModel.update(dish);

            Toast.makeText(getContext(), "Dish updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Dish can't be saved", Toast.LENGTH_SHORT).show();
        }
    }
}