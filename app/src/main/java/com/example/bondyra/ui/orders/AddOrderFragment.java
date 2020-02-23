package com.example.bondyra.ui.orders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.bondyra.R;

import javax.xml.transform.Result;

public class AddOrderFragment extends Fragment {
//
//    public static final String EXTRA_DISHES =
//            "com.example.bondyra.ui.orders.EXTRA_DISHES";
//
//    public static final String EXTRA_TABLE =
//            "com.example.bondyra.ui.orders.EXTRA_TABLE";
//
//    private EditText editTextDishes;
//    private NumberPicker numberPickerTable;
//    private Button buttonAdd;
//
//    public View onCreate(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//        View root = inflater.inflate(R.layout.fragment_add_order, container, false);
//
//        editTextDishes = root.findViewById(R.id.dishes_et);
//        numberPickerTable = root.findViewById(R.id.table_number_picker);
//        buttonAdd = root.findViewById(R.id.add_order_btn);
//
//        numberPickerTable.setMinValue(1);
//        numberPickerTable.setMaxValue(10);
//
//
//
//        return root;
//    }
//
//    private void saveOrder() {
//        String dishes = editTextDishes.getText().toString();
//        int table = numberPickerTable.getValue();
//
//        if(dishes.trim().isEmpty()) {
//            Toast.makeText(getContext(), "Please insert dishes", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        Intent data = new Intent();
//        data.putExtra(EXTRA_DISHES, dishes);
//        data.putExtra(EXTRA_TABLE, table);
//
//
//    }
//
//    private void goAddFood() {
////        Bundle bundle = new Bundle();
////        bundle.putString("date", date);
//        Fragment fragment = null;
//        fragment = new OrdersFragment();
////        fragment.setArguments(bundle);
//        replaceFragment(fragment);
//    }
//
//    private void replaceFragment(Fragment fragment) {
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        transaction.replace(R.id.fragment_container, fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
//    }
}
