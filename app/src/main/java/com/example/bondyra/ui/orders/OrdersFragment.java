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
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bondyra.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static android.app.Activity.RESULT_OK;

public class OrdersFragment extends Fragment {

    public static final int ADD_ORDER_REQUEST = 1;
    public static final int EDIT_ORDER_REQUEST = 2;

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

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                ordersViewModel.delete(adapter.getOrderAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getContext(), "Order deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new OrderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Order order) {
                Intent intent = new Intent(getContext(), AddOrderActivity.class);
                intent.putExtra(AddOrderActivity.EXTRA_ID, order.getId());
                intent.putExtra(AddOrderActivity.EXTRA_TIME, order.getTimeOfPlaced());
                intent.putExtra(AddOrderActivity.EXTRA_COST, order.getCost());
                intent.putExtra(AddOrderActivity.EXTRA_STATUS, order.getStatus());
                intent.putExtra(AddOrderActivity.EXTRA_DISHES, order.getDishes());
                intent.putExtra(AddOrderActivity.EXTRA_TABLE, order.getNumberOfTable());
                startActivityForResult(intent, EDIT_ORDER_REQUEST);
            }
        });

        return root;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_ORDER_REQUEST && resultCode == RESULT_OK) {
            String dishes = data.getStringExtra(AddOrderActivity.EXTRA_DISHES);
            int table = data.getIntExtra(AddOrderActivity.EXTRA_TABLE, 1);
            int status = data.getIntExtra(AddOrderActivity.EXTRA_STATUS, 1);
            double cost = data.getDoubleExtra(AddOrderActivity.EXTRA_COST, 0.0);

            Order order = new Order(dishes, table, status, cost);
            ordersViewModel.insert(order);
            Toast.makeText(getContext(), "Order saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_ORDER_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddOrderActivity.EXTRA_ID, -1);

            if (id == -1) {
                Toast.makeText(getContext(), "Order can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String dishes = data.getStringExtra(AddOrderActivity.EXTRA_DISHES);
            int table = data.getIntExtra(AddOrderActivity.EXTRA_TABLE, 1);
            int status = data.getIntExtra(AddOrderActivity.EXTRA_STATUS, 1);

            Order order = new Order(dishes, table, status);
            order.setId(id);
            ordersViewModel.update(order);

            Toast.makeText(getContext(), "Order updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Order can't be saved", Toast.LENGTH_SHORT).show();
        }
    }
}