package com.example.coe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentConfirmActivity extends AppCompatActivity {
    public void onCreate(Bundle SavedInstance){
        super.onCreate(SavedInstance);
        setContentView(R.layout.confirm_payment);
        Intent get_details = getIntent();
        String payment_details = get_details.getStringExtra("mode_payment");
        String amount = get_details.getStringExtra("amount");
        TextView amoun = (TextView) findViewById(R.id.amount_view);
        TextView details = (TextView) findViewById(R.id.amount_method);
        amoun.setText(amount);
        details.setText(payment_details);
        Button proc = (Button) findViewById(R.id.confirm);
        proc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent success = new Intent(PaymentConfirmActivity.this,PaymentSuccess.class);
                startActivity(success);
            }
        });
        Button change = (Button) findViewById(R.id.change_pay);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
