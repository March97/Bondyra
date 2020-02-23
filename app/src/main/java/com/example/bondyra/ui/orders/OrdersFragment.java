package com.example.bondyra.ui.orders;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bondyra.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static android.app.Activity.RESULT_OK;

public class OrdersFragment extends Fragment {

    public static final int ADD_ORDER_REQUEST = 1;

    private OrdersViewModel ordersViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_orders, container, false);

        FloatingActionButton buttonAddOrder = root.findViewById(R.id.orders_fab);
        buttonAddOrder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddOrderActivity.class);
                startActivityForResult(intent, ADD_ORDER_REQUEST);
            }
        });

        RecyclerView recyclerView = root.findViewById(R.id.orders_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        final OrderAdapter adapter = new OrderAdapter();
        recyclerView.setAdapter(adapter);

        ordersViewModel = ViewModelProviders.of(this).get(OrdersViewModel.class);
        ordersViewModel.getAllOrders().observe(this, new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                adapter.setOrders(orders);
                Toast.makeText(getContext(), "onChanged", Toast.LENGTH_SHORT).show();
            }
        });


        return root;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_ORDER_REQUEST && resultCode == RESULT_OK) {
            String dishes = data.getStringExtra(AddOrderActivity.EXTRA_DISHES);
            int table = data.getIntExtra(AddOrderActivity.EXTRA_TABLE, 1);

            Order order = new Order(dishes, table);
            ordersViewModel.insert(order);
            Toast.makeText(getContext(), "Order saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Order can't be saved", Toast.LENGTH_SHORT).show();
        }
    }
}