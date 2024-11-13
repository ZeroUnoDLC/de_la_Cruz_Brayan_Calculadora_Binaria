package com.example.tareacalculadorabinaria;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private EditText etNumber1, etNumber2;
    private TextView tvResult;
    private Button btnAdd, btnSubtract, btnMultiply, btnDivide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setContentView(R.layout.activity_main);

        etNumber1 = findViewById(R.id.editTextNumber);
        etNumber2 = findViewById(R.id.editTextNumber2);
        tvResult = findViewById(R.id.textView3);
        btnAdd = findViewById(R.id.buttonSumar);
        btnSubtract = findViewById(R.id.buttonRestar);
        btnMultiply = findViewById(R.id.buttonMultiplicar);
        btnDivide = findViewById(R.id.buttonDividir);

        btnAdd.setOnClickListener(view -> performOperation("add"));
        btnSubtract.setOnClickListener(view -> performOperation("subtract"));
        btnMultiply.setOnClickListener(view -> performOperation("multiply"));
        btnDivide.setOnClickListener(view -> performOperation("divide"));

    }

    private void performOperation(String operation) {
        String binary1 = etNumber1.getText().toString();
        String binary2 = etNumber2.getText().toString();

        // Validar que las entradas sean números binarios válidos
        if (!isBinary(binary1) || !isBinary(binary2) || binary1.isEmpty()|| binary2.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa números binarios válidos (solo 0s y 1s)", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convertir a decimal
        int decimal1 = Integer.parseInt(binary1, 2);
        int decimal2 = Integer.parseInt(binary2, 2);
        int resultDecimal = 0;
            switch (operation) {
                case "add":
                    resultDecimal = decimal1 + decimal2;
                    break;
                case "subtract":
                    resultDecimal = decimal1 - decimal2;
                    break;
                case "multiply":
                    resultDecimal = decimal1 * decimal2;
                    break;
                case "divide":
                    if (decimal2 == 0) {
                        Toast.makeText(this, "No se puede dividir entre cero", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    resultDecimal = decimal1 / decimal2;
                    break;
            }

        // Convertir resultado a binario
        String resultBinary = Integer.toBinaryString(resultDecimal);
        tvResult.setText("= " + resultBinary);
    }

    // Función para validar binario
    private boolean isBinary(String number) {
        for (char c : number.toCharArray()) {
            if (c != '0' && c != '1') {
                return false;
            }
        }
        return true;
    }
}