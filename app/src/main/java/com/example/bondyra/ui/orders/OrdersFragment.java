package com.example.bondyra.ui.orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bondyra.R;
import com.example.bondyra.objects.Order;

import java.util.List;

public class OrdersFragment extends Fragment {

    private OrdersViewModel ordersViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_orders, container, false);

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

        //        final TextView textView = root.findViewById(R.id.orders_tv);
//        ordersViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        return root;
    }
}