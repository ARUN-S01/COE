package com.example.coe.payment;

import static com.example.coe.examactivity.ExamsAdapter.clicked_exam_date;
import static com.example.coe.examactivity.ExamsAdapter.clicked_exam_fee;
import static com.example.coe.examactivity.ExamsAdapter.clicked_exam_name;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coe.R;

public class PaymentConfirmActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle SavedInstance){
        super.onCreate(SavedInstance);
        //setContentView(R.layout.confirm_payment);
        Intent get_details = getIntent();
        String payment_details = get_details.getStringExtra("mode_payment");
       // String amount = get_details.getStringExtra("amount");
        TextView amoun = (TextView) findViewById(R.id.pay_exam_fees);
        TextView details = (TextView) findViewById(R.id.pay_mode);
        amoun.setText(clicked_exam_fee);
        details.setText(payment_details);
        TextView pay_course = (TextView) findViewById(R.id.pay_course_name);
        TextView pay_date = (TextView) findViewById(R.id.pay_exam_date);
        pay_course.setText(clicked_exam_name);
        pay_date.setText(clicked_exam_date);
        Button proc = (Button) findViewById(R.id.confirm);
        proc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent success = new Intent(PaymentConfirmActivity.this, PaymentSuccess.class);
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
