package com.sudoorigin.calculator;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView enteredValue;
    double v1, v2, mV;
    boolean sumCheck, subCheck, mulCheck, divCheck, modCheck,expoCheck,pointBtnOverlap;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        pointBtnOverlap = true;  //so that there will not be more than one point.
        AlertDialog.Builder newAlert = new AlertDialog.Builder(this);
        newAlert.setTitle("Please note!")
                .setMessage("1) Multiple entries not supported yet.\n2) Avoid clicking again on symbols(+,-,x,\u00F7,%,^), it will do wrong calculation.\n3) If you clicked by mistake click on cancel(C) button then enter values again \n4) If UI expended more than screen size after calculation click on 'C' it will restore. \n   Thank you!")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setPositiveButton("Done", null)
                .setNegativeButton("Exit", (dialog, which) -> finish())
                .show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enteredValue = findViewById(R.id.enteredValue);

        Button one = findViewById(R.id.one);
        Button two = findViewById(R.id.two);
        Button three = findViewById(R.id.three);
        Button four = findViewById(R.id.four);
        Button five = findViewById(R.id.five);
        Button six = findViewById(R.id.six);
        Button seven = findViewById(R.id.seven);
        Button eight = findViewById(R.id.eight);
        Button nine = findViewById(R.id.nine);
        Button zero = findViewById(R.id.zero);
        Button doubleZero = findViewById(R.id.doubleZero);
        Button point = findViewById(R.id.point);
        Button clear = findViewById(R.id.reset);
        Button sum = findViewById(R.id.sum);
        Button sub = findViewById(R.id.sub);
        Button mul = findViewById(R.id.mul);
        Button div = findViewById(R.id.div);
        Button mod = findViewById(R.id.mod);
        Button expo = findViewById(R.id.expo);
        Button equalResult = findViewById(R.id.equalResult);


        one.setOnClickListener(v -> enteredValue.setText(enteredValue.getText().toString() + "1"));
        two.setOnClickListener(v -> enteredValue.setText(enteredValue.getText().toString() + "2"));
        three.setOnClickListener(v -> enteredValue.setText(enteredValue.getText().toString() + "3"));
        four.setOnClickListener(v -> enteredValue.setText(enteredValue.getText().toString() + "4"));
        five.setOnClickListener(v -> enteredValue.setText(enteredValue.getText().toString() + "5"));
        six.setOnClickListener(v -> enteredValue.setText(enteredValue.getText().toString() + "6"));
        seven.setOnClickListener(v -> enteredValue.setText(enteredValue.getText().toString() + "7"));
        eight.setOnClickListener(v -> enteredValue.setText(enteredValue.getText().toString() + "8"));
        nine.setOnClickListener(v -> enteredValue.setText(enteredValue.getText().toString() + "9"));
        zero.setOnClickListener(v -> enteredValue.setText(enteredValue.getText().toString() + "0"));
        doubleZero.setOnClickListener(v -> enteredValue.setText(enteredValue.getText().toString() + "00"));
        point.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (pointBtnOverlap){
                    enteredValue.setText(enteredValue.getText().toString()+ ".");
                    pointBtnOverlap = false;
                }
            }
        });

        clear.setOnClickListener(v -> {
            enteredValue.setText("");
            TextView result = findViewById(R.id.result);
            result.setText("");
            pointBtnOverlap = true;   //after clearing point button should be active again
        });

        //check if sum is clicked
        sum.setOnClickListener(v -> {
            checkInput();
            v1 = Double.parseDouble(enteredValue.getText().toString());
            enteredValue.setText("");
            sumCheck = true;
            subCheck = false;
            mulCheck = false;
            divCheck = false;
            modCheck = false;
            pointBtnOverlap = true;   //after clicking point button should be active again
            TextView result = findViewById(R.id.result);
            result.setText("+ ...?");
        });
        //check if sub is clicked
        sub.setOnClickListener(v -> {
            checkInput();
            v1 = Double.parseDouble(enteredValue.getText().toString());
            enteredValue.setText("");
            sumCheck = false;
            subCheck = true;
            mulCheck = false;
            divCheck = false;
            modCheck = false;
            pointBtnOverlap = true;   //after clicking point button should be active again
            TextView result = findViewById(R.id.result);
            result.setText("- ...?");

        });
        //check if mul is clicked
        mul.setOnClickListener(v -> {
            checkInput();
            v1 = Double.parseDouble(enteredValue.getText().toString());
            enteredValue.setText("");
            sumCheck = false;
            subCheck = false;
            mulCheck = true;
            divCheck = false;
            modCheck = false;
            pointBtnOverlap = true;   //after clicking point button should be active again

            TextView result = findViewById(R.id.result);
            result.setText("x ...?");
        });
        //check if div is clicked
        div.setOnClickListener(v -> {
            checkInput();
            v1 = Double.parseDouble(enteredValue.getText().toString());
            enteredValue.setText("");
            sumCheck = false;
            subCheck = false;
            mulCheck = false;
            divCheck = true;
            modCheck = false;
            pointBtnOverlap = true;   //after clicking point button should be active again

            TextView result = findViewById(R.id.result);
            result.setText("\u00F7 ...?");
        });
        //check if mod is clicked
        mod.setOnClickListener(v -> {
            checkInput();
            v1 = Double.parseDouble(enteredValue.getText().toString());
            enteredValue.setText("");
            sumCheck = false;
            subCheck = false;
            mulCheck = false;
            divCheck = false;
            modCheck = true;
            pointBtnOverlap = true;   //after clicking point button should be active again

            TextView result = findViewById(R.id.result);
            result.setText("% ...?");
        });
        expo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInput();
                v1 = Double.parseDouble(enteredValue.getText().toString());
                mV = v1*v1;
                TextView result = findViewById(R.id.result);
                result.setText(String.valueOf(mV));
                enteredValue.setText("");
                expoCheck = false;
            }
        });

        equalResult.setOnClickListener(v -> {
            checkInput(); //it will check first input
            pointBtnOverlap = true;   //after calculation point button should be active again
            v2 = Double.parseDouble(enteredValue.getText().toString());
            checkInput(); //it will check second input
            if (sumCheck) {   //if sumCheck is true then it will do addition
                mV = v1 + v2;
                displayResult(Math.round(mV*1000d)/1000d);
                enteredValue.setText("");
                sumCheck = false;
            } else if (subCheck) {   //if subCheck is true then it will do addition
                mV = v1 - v2;
                displayResult(Math.round(mV*1000d)/1000d);
                enteredValue.setText("");
                subCheck = false;
            } else if (mulCheck) {   //if mulCheck is true then it will do addition
                mV = v1 * v2;
                displayResult(Math.round(mV*1000d)/1000d);
                enteredValue.setText("");
                mulCheck = false;
            } else if (divCheck) {   //if divCheck is true then it will do addition
                mV = v1 / v2;
                displayResult(Math.round(mV*1000d)/1000d);
                enteredValue.setText("");
                divCheck = false;
            } else{   //if modCheck is true then it will do addition
                mV = v1 % v2;
                displayResult(Math.round(mV*1000d)/1000d);
                enteredValue.setText("");
                modCheck = false;
            }
        });




    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.calc_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.about_app:
                gameAlert();
                return true;
            case R.id.about_us:
                aboutUs();
                return true;
            case R.id.exit:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //.setNegativeButton("Exit", (dialog, which) -> finish())
    public void gameAlert(){
        AlertDialog.Builder newAlert = new AlertDialog.Builder(this);
        newAlert.setTitle("Please note!")
                .setMessage("1) Multiple entries not supported yet.\n2) Avoid clicking again on symbols(+,-,x,\u00F7,%,^), it will do wrong calculation.\n3) If you clicked by mistake click on cancel(C) button then enter values again \n4) If UI expended more than screen size after calculation click on 'C' it will restore. \n   Thank you!")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setPositiveButton("Ok", null)
                .show();
    }
    public  void aboutUs(){
        AlertDialog.Builder newAlert = new AlertDialog.Builder(this);
        newAlert.setTitle("Please note!")
                .setMessage("Hi, my name is Ambuj Singh. thank you for using my app.")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setPositiveButton("Ok", null)
                .show();
    }

    public void displayResult(double mV) {
        checkInput();   //it will handle if user didn't enter any value
        TextView result = findViewById(R.id.result);
        result.setText(String.valueOf(mV));
        enteredValue.setText("");
        result.setTextColor(Color.parseColor("#000000"));
    }

    public void checkInput() {
        if (enteredValue.getText().toString().matches("")) {
            enteredValue.setText("0");
        }
    }

}
