package com.yeumkyuseok.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText number1 = (EditText) findViewById(R.id.number1);
        EditText number2 = (EditText) findViewById(R.id.number2);
        TextView result = (TextView) findViewById(R.id.result);

        Button buttonPlus = (Button) findViewById(R.id.buttonPlus);
        Button buttonMinus = (Button) findViewById(R.id.buttonMinus);
        Button buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
        Button buttonDivide = (Button) findViewById(R.id.buttonDivide);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double resultValue;
                String value1 = number1.getText().toString();
                String value2 = number2.getText().toString();

                //  do calculations if no error in type casting.
                try {
                    if (v.getId() == R.id.buttonPlus) {
                        resultValue = Double.valueOf(value1) + Double.valueOf(value2);
                        result.setText(String.valueOf(resultValue));
                    } else if (v.getId() == R.id.buttonMinus) {
                        resultValue = Double.valueOf(value1) - Double.valueOf(value2);
                        result.setText(String.valueOf(resultValue));
                    } else if (v.getId() == R.id.buttonMultiply) {
                        resultValue = Double.valueOf(value1) * Double.valueOf(value2);
                        result.setText(String.valueOf(resultValue));
                    } else if (v.getId() == R.id.buttonDivide) {
                        resultValue = Double.valueOf(value1) / Double.valueOf(value2);
                        result.setText(String.valueOf(resultValue));
                    }

                } catch (NumberFormatException e) {
                    number1.setText("");
                    number2.setText("");
                    result.setText("Result");
                }

            }
        };

        buttonPlus.setOnClickListener(onClickListener);
        buttonMinus.setOnClickListener(onClickListener);
        buttonMultiply.setOnClickListener(onClickListener);
        buttonDivide.setOnClickListener(onClickListener);

    }
}