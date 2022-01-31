package com.example.coe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.coe.payment.FeePaymentOptionActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent pay = new Intent(MainActivity.this, FeePaymentOptionActivity.class);
        startActivity(pay);

    }
}