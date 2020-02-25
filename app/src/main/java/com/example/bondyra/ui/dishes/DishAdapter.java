package com.example.bondyra.ui.dishes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bondyra.R;

import java.util.ArrayList;
import java.util.List;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.DishHolder> {

    private List<Dish> dishes = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public DishHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_dish, parent, false);
        return new DishHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DishHolder holder, int position) {
        Dish currentDish = dishes.get(position);
        holder.dish_id_tv.setText(String.valueOf(currentDish.getId()));
        holder.dish_mass_tv.setText(String.valueOf(currentDish.getMass()));
        holder.dish_name_tv.setText(currentDish.getName());
        holder.dish_price_tv.setText(String.valueOf(currentDish.getPrice()));
    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
        notifyDataSetChanged();
    }

    public Dish getDishAt(int position) {
        return dishes.get(position);
    }

    class DishHolder extends RecyclerView.ViewHolder {
        private TextView dish_id_tv;
        private TextView dish_name_tv;
        private TextView dish_price_tv;
        private TextView dish_mass_tv;

        public DishHolder(View itemView) {
            super(itemView);
            dish_id_tv = itemView.findViewById(R.id.dish_id_tv);
            dish_name_tv = itemView.findViewById(R.id.dish_name_tv);
            dish_price_tv = itemView.findViewById(R.id.dish_price_tv);
            dish_mass_tv = itemView.findViewById(R.id.dish_mass_tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION)
                        listener.onItemClick(dishes.get(position));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Dish dish);
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
