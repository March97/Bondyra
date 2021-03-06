package com.example.bondyra.ui.orders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bondyra.R;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderHolder> {

    private List<Order> orders = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public OrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order, parent, false);
        return new OrderHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHolder holder, int position) {
        Order currentOrder = orders.get(position);
        holder.dishes_tv.setText(currentOrder.getDishes());
        holder.timeOfPlaced_tv.setText("Time of placed: " + currentOrder.getTimeOfPlaced());
        holder.numberOfTable_tv.setText("Table: " + String.valueOf(currentOrder.getNumberOfTable()));
        holder.cost_tv.setText(String.valueOf(currentOrder.getCost()) + " $");
        holder.status_tv.setText("Status: " + statusConverter(currentOrder.getStatus()));
        holder.id_tv.setText(String.valueOf(currentOrder.getId()));

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
        notifyDataSetChanged();
    }

    public Order getOrderAt(int position) {
        return orders.get(position);
    }

    class OrderHolder extends RecyclerView.ViewHolder {
        private TextView dishes_tv;
        private TextView timeOfPlaced_tv;
        private TextView numberOfTable_tv;
        private TextView cost_tv;
        private TextView status_tv;
        private TextView id_tv;

        public OrderHolder(View itemView) {
            super(itemView);
            dishes_tv = itemView.findViewById(R.id.dish_tv);
            timeOfPlaced_tv = itemView.findViewById(R.id.time_tv);
            numberOfTable_tv = itemView.findViewById(R.id.table_tv);
            cost_tv = itemView.findViewById(R.id.cost_tv);
            status_tv = itemView.findViewById(R.id.status_tv);
            id_tv = itemView.findViewById(R.id.id_tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION)
                        listener.onItemClick(orders.get(position));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Order order);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    private String statusConverter (int value) {
        switch (value) {
            case 1:
                return "Placed";
            case 2:
                return "Preparing";
            case 3:
                return "Out";
            case 4:
                return "Paid";
        }
        return "Error";
    }
}
