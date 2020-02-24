package com.example.bondyra.ui.orders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bondyra.R;

import java.util.ArrayList;

public class AddOrderActivity extends AppCompatActivity {

    public static final String EXTRA_ID =
            "com.example.bondyra.ui.orders.EXTRA_ID";

    public static final String EXTRA_TIME =
            "com.example.bondyra.ui.orders.EXTRA_TIME";

    public static final String EXTRA_COST =
            "com.example.bondyra.ui.orders.EXTRA_COST";

    public static final String EXTRA_STATUS =
            "com.example.bondyra.ui.orders.EXTRA_STATUS";

    public static final String EXTRA_DISHES =
            "com.example.bondyra.ui.orders.EXTRA_DISHES";

    public static final String EXTRA_TABLE =
            "com.example.bondyra.ui.orders.EXTRA_TABLE";

    private EditText editTextDishes;
    private NumberPicker numberPickerTable;
    private Button buttonAdd;
    private NumberPicker numberPickerStatus;
    private String[] pickerVals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);

        editTextDishes = findViewById(R.id.dishes_et);
        numberPickerTable = findViewById(R.id.table_number_picker);
        buttonAdd = findViewById(R.id.add_order_btn);
        numberPickerStatus = findViewById(R.id.status_picker);

        numberPickerTable.setMinValue(1);
        numberPickerTable.setMaxValue(10);

        pickerVals = new String[] {"Placed", "Preparing", "Out", "Paid"};
        numberPickerStatus.setMinValue(1);
        numberPickerStatus.setMaxValue(4);
        numberPickerStatus.setDisplayedValues(pickerVals);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Order");
            editTextDishes.setText(intent.getStringExtra(EXTRA_DISHES));
            numberPickerTable.setValue(intent.getIntExtra(EXTRA_TABLE, 1));
            numberPickerStatus.setValue(intent.getIntExtra(EXTRA_STATUS, 1));


        } else {
            setTitle("Add Order");
        }



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
        int status = numberPickerStatus.getValue();

        if(dishes.trim().isEmpty()) {
            Toast.makeText(this, "Please insert dishes", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_DISHES, dishes);
        data.putExtra(EXTRA_TABLE, table);
        data.putExtra(EXTRA_STATUS, status);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

    private String statusConverter(int value) {
        switch (value) {
            case 1:
                return "Placed";
            case 2:
                return "On kitchen";
            case 3:
                return "Out kitchen";
            case 4:
                return "Paid";
        }

        return "Placed";
    }

    private int statusConverter(String value) {
        if (value.compareTo("Placed") == 0)
            return 1;
        else if (value.compareTo("On kitchen") == 0)
            return 2;
        else if (value.compareTo("Out kitchen") == 0)
            return 3;
        else
            return 4;

    }
}
