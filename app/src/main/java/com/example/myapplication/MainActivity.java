package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText inputValue;
    private Spinner sourceUnit;
    private Spinner targetUnit;
    private Button convertButton;
    private TextView outputResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.inputValue);
        sourceUnit = findViewById(R.id.sourceUnit);
        targetUnit = findViewById(R.id.targetUnit);
        convertButton = findViewById(R.id.convertButton);
        outputResult = findViewById(R.id.outputResult);

        ArrayAdapter<CharSequence> unitAdapter = ArrayAdapter.createFromResource(
                this, R.array.units_array, android.R.layout.simple_spinner_item);

        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sourceUnit.setAdapter(unitAdapter);
        targetUnit.setAdapter(unitAdapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertValue();
            }
        });
    }

    private void convertValue() {
        double inputValue = Double.parseDouble(this.inputValue.getText().toString());
        String sourceUnit = this.sourceUnit.getSelectedItem().toString();
        String targetUnit = this.targetUnit.getSelectedItem().toString();
        double result;

        if (sourceUnit.equals("Centimeters") && targetUnit.equals("Meters")) {
            result = inputValue / 100.0;
        } else if (sourceUnit.equals("Grams") && targetUnit.equals("Kilograms")) {
            result = inputValue / 1000.0;
        } else {
            // Handle other conversion cases here
            result = inputValue; // Default to no conversion
        }

        outputResult.setText(String.format("%.2f %s = %.2f %s", inputValue, sourceUnit, result, targetUnit));
    }
}
