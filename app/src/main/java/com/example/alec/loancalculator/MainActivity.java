package com.example.alec.loancalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText car_cost_input;
    private EditText down_payment_input;
    private EditText apr_input;
    private RadioButton lease_button;
    private RadioButton loan_button;
    private SeekBar seek_bar;
    private Button calc_button;
    private TextView monthly_payment;
    private TextView seek_bar_label;
    private TextView seek_bar_months;
    private TextView monthly_payment_total;
    DecimalFormat numberFormat = new DecimalFormat("#.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        car_cost_input = findViewById(R.id.car_cost_input);
        down_payment_input = findViewById(R.id.down_payment_input);
        apr_input = findViewById(R.id.apr_input);
        lease_button = findViewById(R.id.lease_button);
        loan_button = findViewById(R.id.loan_button);
        seek_bar = findViewById(R.id.seek_bar);
        calc_button = findViewById(R.id.calc_button);
        monthly_payment = findViewById(R.id.monthly_payment);
        seek_bar_label = findViewById(R.id.seek_bar_label);
        seek_bar_months = findViewById(R.id.seek_bar_months);
        monthly_payment_total = findViewById(R.id.monthly_payment_total);

        seek_bar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        seek_bar_months.setText(progress+"");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );

        if(savedInstanceState != null){
            monthly_payment_total.setText(savedInstanceState.getString("output"));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("output", monthly_payment_total.getText().toString());
    }

    public void buttonPressed(View v){
        if(loan_button.isChecked()){
            String apr = apr_input.getText().toString();
            double mr = Double.parseDouble(apr)*Math.pow(10, -2)/12;
            String car_cost = car_cost_input.getText().toString();
            int L = Integer.parseInt(car_cost)-Integer.parseInt(down_payment_input.getText().toString());
            int n = seek_bar.getProgress();

            System.out.println(mr);
            System.out.println(n);
            System.out.println(L);

            monthly_payment_total.setText(numberFormat.format(mr*L/(1-Math.pow((1+mr),-n))  ) +"");


        }else if(lease_button.isChecked()){
            String apr = apr_input.getText().toString();
            double mr = Double.parseDouble(apr)*Math.pow(10, -2)/12;
            String car_cost = car_cost_input.getText().toString();
            int dp = Integer.parseInt(down_payment_input.getText().toString());
            int L = Integer.parseInt(car_cost)/3-dp;
            int n = 36;
            seek_bar.setProgress(36);

            monthly_payment_total.setText(numberFormat.format(mr*L/(1-Math.pow((1+mr),-n))  ) +"");
        }

    }
}
