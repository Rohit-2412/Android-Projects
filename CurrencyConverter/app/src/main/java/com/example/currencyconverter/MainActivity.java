package com.example.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Scroller;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void convert(View view) {
        EditText dollarAmount = findViewById(R.id.amt1);
        String dollar = dollarAmount.getText().toString();
        Double dollarInDouble = Double.parseDouble(dollar);

        Double amountInINR = dollarInDouble * 76.27;
//        String finalAns = String.format("%.2f", amountInINR) + " Rupee";
        String finalINR = String.format("%.2f", amountInINR);
//        Toast.makeText(this, finalAns, Toast.LENGTH_SHORT).show();
        EditText inr = findViewById(R.id.amt2);
        inr.setText(finalINR);

        Double amountInPOUND = dollarInDouble * 0.80;
        String finalPOUND = String.format("%.2f", amountInPOUND);
        EditText pound = findViewById(R.id.amt3);
        pound.setText(finalPOUND);

        Double amountInYEN = dollarInDouble * 129.71;
        String finalYEN = String.format("%.2f", amountInYEN);
        EditText yen = findViewById(R.id.amt4);
        yen.setText(finalYEN);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}