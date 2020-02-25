package com.example.bondyra.ui.dishes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bondyra.R;

public class AddDishActivity extends AppCompatActivity {

    public static final String EXTRA_ID =
            "com.example.bondyra.ui.dishes.EXTRA_ID";

    public static final String EXTRA_NAME =
            "com.example.bondyra.ui.dishes.EXTRA_NAME";

    public static final String EXTRA_MASS =
            "com.example.bondyra.ui.dishes.EXTRA_MASS";

    public static final String EXTRA_PRICE =
            "com.example.bondyra.ui.dishes.EXTRA_PRICE";

    private EditText editTextName;
    private EditText editTextPrice;
    private EditText editTextMass;
    private Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dish);

        editTextName = findViewById(R.id.dish_name_et);
        editTextPrice = findViewById(R.id.dish_price_et);
        editTextMass = findViewById(R.id.dish_mass_et);
        buttonAdd = findViewById(R.id.add_dish_btn);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit dish");
            editTextName.setText(intent.getStringExtra(EXTRA_NAME));
            editTextMass.setText(String.valueOf(intent.getIntExtra(EXTRA_MASS, 1)));
            editTextPrice.setText(String.valueOf(intent.getDoubleExtra(EXTRA_PRICE, 1.0)));
        } else {
            setTitle("Add dish");
        }



        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDish();
            }
        });
    }

    private void saveDish() {
        String name = editTextName.getText().toString();
        int mass = Integer.valueOf(editTextMass.getText().toString());
        double price = Double.valueOf(editTextPrice.getText().toString());

        if(name.trim().isEmpty() || mass <= 0 || price <= 0) {
            Toast.makeText(this, "Please insert name", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, name);
        data.putExtra(EXTRA_MASS, mass);
        data.putExtra(EXTRA_PRICE, price);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }
}
