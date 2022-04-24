package com.example.coe.payment;

import static com.example.coe.examactivity.ExamsAdapter.clicked_exam_fee;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coe.R;
import com.example.coe.payment.paymentoptions.GooglePayActivity;
import com.google.android.gms.common.api.GoogleApiActivity;

public class FeePaymentOptionActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedinstance){
        super.onCreate(savedinstance);
        setContentView(R.layout.fee_payment_activity);
        final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        int amount = 2000;
        Button but = (Button) findViewById(R.id.cancelButton);
        final String[] mode_of_payment = {null};

        Button pay = (Button) findViewById(R.id.payment);
        TextView amount_text = (TextView) findViewById(R.id.amount_view);
        amount_text.setText("₹ " + clicked_exam_fee);
        TextView set_method_ = (TextView) findViewById(R.id.set_method);
        if(mode_of_payment[0] == null){
            set_method_.setVisibility(View.INVISIBLE);
        }
        LinearLayout gpay = (LinearLayout) findViewById(R.id.linearLayout2);
        gpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(FeePaymentOptionActivity.this,"Google Pay",Toast.LENGTH_SHORT).show();
                mode_of_payment[0] = "Google Pay";
                set_method_.setVisibility(View.VISIBLE);
                set_method_.setText("Choosen Method: " +mode_of_payment[0]);
                startActivity(new Intent(FeePaymentOptionActivity.this, GooglePayActivity.class));
            }
        });
        LinearLayout paytm = (LinearLayout) findViewById(R.id.linearLayout3);
        paytm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //Toast.makeText(FeePaymentOptionActivity.this,"Paytm",Toast.LENGTH_SHORT).show();
                mode_of_payment[0] = "Paytm";
                set_method_.setVisibility(View.VISIBLE);
                set_method_.setText("Choosen Method: " +mode_of_payment[0]);
            }
        });
        LinearLayout credit_debit = (LinearLayout) findViewById(R.id.linearLayout4);
        credit_debit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //Toast.makeText(FeePaymentOptionActivity.this,"Credit or Debit Card",Toast.LENGTH_SHORT).show();
                mode_of_payment[0] = "Credit or Debit Card";
                set_method_.setVisibility(View.VISIBLE);
                set_method_.setText("Choosen Method: " +mode_of_payment[0]);
            }
        });
        LinearLayout apple_pay = (LinearLayout) findViewById(R.id.linearLayout5);
        apple_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //Toast.makeText(FeePaymentOptionActivity.this,"Apple Pay",Toast.LENGTH_SHORT).show();
                mode_of_payment[0] = "Apple Pay";
                set_method_.setVisibility(View.VISIBLE);
                set_method_.setText("Choosen Method: " +mode_of_payment[0]);
            }
        });
        LinearLayout upi = (LinearLayout) findViewById(R.id.linearLayout6);
        upi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //Toast.makeText(FeePaymentOptionActivity.this,"Upi Payment",Toast.LENGTH_SHORT).show();
                mode_of_payment[0] = "Upi Payment";
                set_method_.setVisibility(View.VISIBLE);
                set_method_.setText("Choosen Method: " +mode_of_payment[0]);
            }
        });

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final VibrationEffect vibrationEffect2;
                if(mode_of_payment[0] == null){
                    Toast.makeText(FeePaymentOptionActivity.this,"Please Choose Payment Mode",Toast.LENGTH_SHORT).show();
                    pay.startAnimation(AnimationUtils.loadAnimation(FeePaymentOptionActivity.this,R.anim.shake));
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                        vibrationEffect2 = VibrationEffect.createPredefined(VibrationEffect.EFFECT_DOUBLE_CLICK);
                        vibrator.cancel();
                        vibrator.vibrate(vibrationEffect2);
                    }
                }
                else{
                    Toast.makeText(FeePaymentOptionActivity.this,"Proceeding..",Toast.LENGTH_SHORT).show();
                    //Intent pass_details = new Intent(FeePaymentOptionActivity.this, PaymentConfirmActivity.class);
                    //pass_details.putExtra("mode_payment",mode_of_payment[0]);
                   // pass_details.putExtra("amount",clicked_exam_fee);
                    //startActivity(pass_details);
                }
            }
        });
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
