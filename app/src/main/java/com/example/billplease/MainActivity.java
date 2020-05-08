package com.example.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    EditText etAmt;
    EditText etPax;
    ToggleButton btnSvs;
    ToggleButton btnGst;
    EditText etDisc;
    TextView tvTotalBill;
    TextView tvPay;
    Button btnSplit;
    Button btnReset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAmt = findViewById(R.id.etAmt);
        etPax = findViewById(R.id.etPax);
        btnSvs = findViewById(R.id.tbSvs);
        btnGst = findViewById(R.id.tbGst);
        etDisc = findViewById(R.id.etDisc);
        tvTotalBill = findViewById(R.id.tvTotalBill);
        tvPay = findViewById(R.id.tvPay);
        btnSplit = findViewById(R.id.btnSplit);
        btnReset = findViewById(R.id.btnReset);

       btnSplit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               double GstAmt = 0;


               if(!btnSvs.isChecked() && !btnGst.isChecked()){
                   GstAmt = Double.parseDouble(etAmt.getText().toString());
               }
               else if(btnSvs.isChecked() && !btnGst.isChecked()){
                   GstAmt = Double.parseDouble(etAmt.getText().toString()) * 1.1;
               }
               else if(!btnSvs.isChecked() && btnGst.isChecked()){
                   GstAmt = Double.parseDouble(etAmt.getText().toString()) * 1.07;
               }
               else{
                    GstAmt = Double.parseDouble(etAmt.getText().toString()) * 1.17;
               }

               if(etDisc.getText().toString().trim().length()!=0){
                  GstAmt *= 1- Double.parseDouble(etDisc.getText().toString())/100;
               }

               tvTotalBill.setText("Total Bill: $" + String.format("%.2f", GstAmt));
               int people = Integer.parseInt(etPax.getText().toString());
               if(people !=1){
                   tvPay.setText("Each Pay: $" + String.format("%.2f", GstAmt / people));
               }
               else{
                   tvPay.setText("Each Pays: $" + GstAmt);
               }
           }

       });


    btnReset.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            btnGst.setChecked(false);
            btnSvs.setChecked(false);
            etPax.setText("");
            etAmt.setText("");
            etDisc.setText("");
            tvTotalBill.setText("Total Bill: $");
            tvPay.setText("Each Pays: $");
        }


    });


    }
}
