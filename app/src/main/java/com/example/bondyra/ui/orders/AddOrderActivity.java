package com.example.bondyra.ui.orders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.bondyra.R;

public class AddOrderActivity extends AppCompatActivity {

    public static final String EXTRA_DISHES =
            "com.example.bondyra.ui.orders.EXTRA_DISHES";

    public static final String EXTRA_TABLE =
            "com.example.bondyra.ui.orders.EXTRA_TABLE";

    private EditText editTextDishes;
    private NumberPicker numberPickerTable;
    private Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);

        editTextDishes = findViewById(R.id.dishes_et);
        numberPickerTable = findViewById(R.id.table_number_picker);
        buttonAdd = findViewById(R.id.add_order_btn);

        numberPickerTable.setMinValue(1);
        numberPickerTable.setMaxValue(10);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveOrder();
            }
        });
    }

    private void saveOrder() {
        String dishes = editTextDishes.getText().toString();
        int table = numberPickerTable.getValue();

        if(dishes.trim().isEmpty()) {
            Toast.makeText(this, "Please insert dishes", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_DISHES, dishes);
        data.putExtra(EXTRA_TABLE, table);

        setResult(RESULT_OK, data);
        finish();
    }
}
